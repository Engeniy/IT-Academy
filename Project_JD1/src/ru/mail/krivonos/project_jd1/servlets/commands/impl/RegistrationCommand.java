package ru.mail.krivonos.project_jd1.servlets.commands.impl;

import ru.mail.krivonos.project_jd1.config.ConfigurationManagerImpl;
import ru.mail.krivonos.project_jd1.config.PropertiesVariables;
import ru.mail.krivonos.project_jd1.services.ProfileService;
import ru.mail.krivonos.project_jd1.services.UserService;
import ru.mail.krivonos.project_jd1.services.exceptions.RegistrationException;
import ru.mail.krivonos.project_jd1.services.impl.ProfileServiceImpl;
import ru.mail.krivonos.project_jd1.services.impl.UserServiceImpl;
import ru.mail.krivonos.project_jd1.services.model.ProfileDTO;
import ru.mail.krivonos.project_jd1.services.model.user.UserInfoDTO;
import ru.mail.krivonos.project_jd1.services.model.user.UserRegistrationDTO;
import ru.mail.krivonos.project_jd1.servlets.commands.Command;
import ru.mail.krivonos.project_jd1.servlets.validators.ProfileDTOValidator;
import ru.mail.krivonos.project_jd1.servlets.validators.UserRegistrationDTOValidator;
import ru.mail.krivonos.project_jd1.servlets.validators.impl.ProfileDTOValidatorImpl;
import ru.mail.krivonos.project_jd1.servlets.validators.impl.UserRegistrationDTOValidatorImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class RegistrationCommand implements Command {

    private UserService userService = UserServiceImpl.getInstance();

    private ProfileService profileService = ProfileServiceImpl.getInstance();

    private UserRegistrationDTOValidator userDTOValidator = UserRegistrationDTOValidatorImpl.getInstance();

    private ProfileDTOValidator profileDTOValidator = ProfileDTOValidatorImpl.getInstance();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        Map<String, String> messages = new HashMap<>();
        req.setAttribute("messages", messages);
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        UserRegistrationDTO userRegistrationDTO = new UserRegistrationDTO();
        userRegistrationDTO.setName(name);
        userRegistrationDTO.setSurname(surname);
        userRegistrationDTO.setEmail(email);
        userRegistrationDTO.setPassword(password);
        userDTOValidator.validate(messages, userRegistrationDTO);

        String address = req.getParameter("address");
        String telephone = req.getParameter("telephone");
        UserInfoDTO userInfoDTO = new UserInfoDTO();
        userInfoDTO.setEmail(email);
        userInfoDTO.setName(name);
        userInfoDTO.setSurname(surname);
        ProfileDTO profileDTO = new ProfileDTO();
        profileDTO.setUser(userInfoDTO);
        profileDTO.setAddress(address);
        profileDTO.setTelephone(telephone);
        profileDTOValidator.validate(messages, profileDTO);

        if (!messages.isEmpty()) {
            return ConfigurationManagerImpl.getInstance().getProperty(PropertiesVariables.REGISTRATION_PAGE_PATH);
        }
        try {
            userService.add(userRegistrationDTO);
        } catch (RegistrationException e) {
            req.setAttribute("error", "Registration error! The same email exists!");
            return ConfigurationManagerImpl.getInstance().getProperty(PropertiesVariables.REGISTRATION_PAGE_PATH);
        }
        profileService.add(profileDTO);
        req.setAttribute("message", "Successfully registered!");
        return ConfigurationManagerImpl.getInstance().getProperty(PropertiesVariables.LOGIN_PAGE_PATH);
    }
}
