package ru.mail.krivonos.project_jd1.servlets.commands.impl;

import ru.mail.krivonos.project_jd1.config.ConfigurationManagerImpl;
import ru.mail.krivonos.project_jd1.config.PropertiesVariables;
import ru.mail.krivonos.project_jd1.services.ProfileService;
import ru.mail.krivonos.project_jd1.services.UserService;
import ru.mail.krivonos.project_jd1.services.exceptions.PasswordChangeException;
import ru.mail.krivonos.project_jd1.services.impl.ProfileServiceImpl;
import ru.mail.krivonos.project_jd1.services.impl.UserServiceImpl;
import ru.mail.krivonos.project_jd1.services.model.ProfileDTO;
import ru.mail.krivonos.project_jd1.services.model.user.UserInfoDTO;
import ru.mail.krivonos.project_jd1.servlets.commands.Command;
import ru.mail.krivonos.project_jd1.servlets.util.ValidationUtil;
import ru.mail.krivonos.project_jd1.servlets.validators.ProfileDTOValidator;
import ru.mail.krivonos.project_jd1.servlets.validators.UserInfoDTOValidator;
import ru.mail.krivonos.project_jd1.servlets.validators.impl.ProfileDTOValidatorImpl;
import ru.mail.krivonos.project_jd1.servlets.validators.impl.UserInfoDTOValidatorImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class ProfileUpdateCommand implements Command {

    private UserService userService = UserServiceImpl.getInstance();

    private ProfileService profileService = ProfileServiceImpl.getInstance();

    private UserInfoDTOValidator userDTOValidator = UserInfoDTOValidatorImpl.getInstance();

    private ProfileDTOValidator profileDTOValidator = ProfileDTOValidatorImpl.getInstance();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        Map<String, String> messages = new HashMap<>();
        req.setAttribute("messages", messages);

        String oldPassword = req.getParameter("old-password");
        String newPassword = req.getParameter("new-password");
        String email = req.getParameter("email");
        if (!oldPassword.trim().isEmpty() && !newPassword.trim().isEmpty()) {
            ValidationUtil.validatePassword(messages, oldPassword);
            ValidationUtil.validatePassword(messages, newPassword);
            if (messages.isEmpty()) {
                try {
                    userService.updatePassword(email, oldPassword, newPassword);
                } catch (PasswordChangeException e) {
                    messages.put("password", "Incorrect password!");
                }
            } else {
                return ConfigurationManagerImpl.getInstance().getProperty(PropertiesVariables.PROFILE_PAGE_PATH);
            }
        }
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        UserInfoDTO userInfoDTO = new UserInfoDTO();
        userInfoDTO.setName(name);
        userInfoDTO.setSurname(surname);
        userInfoDTO.setEmail(email);
        userDTOValidator.validate(messages, userInfoDTO);

        String address = req.getParameter("address");
        String telephone = req.getParameter("telephone");
        ProfileDTO profileDTO = new ProfileDTO();
        profileDTO.setUser(userInfoDTO);
        profileDTO.setAddress(address);
        profileDTO.setTelephone(telephone);
        profileDTOValidator.validate(messages, profileDTO);

        req.setAttribute("profile", profileDTO);
        if (!messages.isEmpty()) {
            return ConfigurationManagerImpl.getInstance().getProperty(PropertiesVariables.PROFILE_PAGE_PATH);
        }
        userService.updateInfo(userInfoDTO);
        profileService.update(profileDTO);
        req.setAttribute("message", "Profile successfully updated!");
        return ConfigurationManagerImpl.getInstance().getProperty(PropertiesVariables.PROFILE_PAGE_PATH);
    }
}
