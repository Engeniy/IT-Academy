package ru.mail.krivonos.project_jd1.servlets.command;

import ru.mail.krivonos.project_jd1.config.ConfigurationManagerImpl;
import ru.mail.krivonos.project_jd1.config.PropertiesVariables;
import ru.mail.krivonos.project_jd1.services.ItemService;
import ru.mail.krivonos.project_jd1.services.UserService;
import ru.mail.krivonos.project_jd1.services.impl.ItemServiceImpl;
import ru.mail.krivonos.project_jd1.services.impl.UserServiceImpl;
import ru.mail.krivonos.project_jd1.services.model.user.AuthorizedUserDTO;
import ru.mail.krivonos.project_jd1.services.model.user.UserLoginDTO;
import ru.mail.krivonos.project_jd1.servlets.model.CommandEnum;
import ru.mail.krivonos.project_jd1.servlets.model.Constants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginCommand implements Command {

    private UserService userService = UserServiceImpl.getInstance();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        if (email != null && !email.equals("")) {
            UserLoginDTO userLoginDTO = new UserLoginDTO();
            userLoginDTO.setEmail(email);
            userLoginDTO.setPassword(password);
            AuthorizedUserDTO authorizedUserDTO = userService.loginUser(userLoginDTO);
            if (authorizedUserDTO != null) {
                HttpSession session = req.getSession();
                session.setAttribute(Constants.SESSION_USER_KEY, authorizedUserDTO);
                resp.sendRedirect(req.getContextPath() + Constants.DEFAULT_URL +
                        CommandEnum.ITEMS.name().toLowerCase());
                return null;
            } else {
                req.setAttribute("error", "Invalid email or password!");
                return ConfigurationManagerImpl.getInstance().getProperty(PropertiesVariables.LOGIN_PAGE_PATH);
            }
        } else {
            req.setAttribute("error", "Invalid email or password!");
            return ConfigurationManagerImpl.getInstance().getProperty(PropertiesVariables.LOGIN_PAGE_PATH);
        }
    }
}
