package ru.mail.krivonos.project_jd1.servlets.commands.impl;

import ru.mail.krivonos.project_jd1.config.ConfigurationManagerImpl;
import ru.mail.krivonos.project_jd1.config.PropertiesVariables;
import ru.mail.krivonos.project_jd1.services.UserService;
import ru.mail.krivonos.project_jd1.services.impl.UserServiceImpl;
import ru.mail.krivonos.project_jd1.services.model.user.AuthorizedUserDTO;
import ru.mail.krivonos.project_jd1.services.model.user.UserLoginDTO;
import ru.mail.krivonos.project_jd1.servlets.commands.Command;
import ru.mail.krivonos.project_jd1.servlets.model.CommandEnum;
import ru.mail.krivonos.project_jd1.servlets.model.Constants;
import ru.mail.krivonos.project_jd1.servlets.validators.UserLoginDTOValidator;
import ru.mail.krivonos.project_jd1.servlets.validators.impl.UserLoginDTOValidatorImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class LoginCommand implements Command {

    private UserService userService = UserServiceImpl.getInstance();

    private UserLoginDTOValidator userLoginDTOValidator = UserLoginDTOValidatorImpl.getInstance();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Map<String, String> messages = new HashMap<>();
        req.setAttribute("messages", messages);
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        UserLoginDTO userLoginDTO = new UserLoginDTO();
        userLoginDTO.setEmail(email);
        userLoginDTO.setPassword(password);
        userLoginDTOValidator.validate(messages, userLoginDTO);

        if (!messages.isEmpty()) {
            return ConfigurationManagerImpl.getInstance().getProperty(PropertiesVariables.LOGIN_PAGE_PATH);
        }
        AuthorizedUserDTO authorizedUserDTO = userService.loginUser(userLoginDTO);
        if (authorizedUserDTO != null) {
            HttpSession session = req.getSession();
            session.setAttribute(Constants.SESSION_USER_KEY, authorizedUserDTO);
            resp.sendRedirect(req.getContextPath() + Constants.DEFAULT_URL +
                    CommandEnum.ITEMS.name().toLowerCase());
            return null;
        } else {
            messages.put("login", "Invalid email or password!");
            return ConfigurationManagerImpl.getInstance().getProperty(PropertiesVariables.LOGIN_PAGE_PATH);
        }

    }
}
