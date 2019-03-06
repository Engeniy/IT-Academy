package ru.mail.krivonos.project_jd1.servlets.command;

import ru.mail.krivonos.project_jd1.config.ConfigurationManagerImpl;
import ru.mail.krivonos.project_jd1.config.PropertiesVariables;
import ru.mail.krivonos.project_jd1.services.ProfileService;
import ru.mail.krivonos.project_jd1.services.UserService;
import ru.mail.krivonos.project_jd1.services.exceptions.PasswordChangeException;
import ru.mail.krivonos.project_jd1.services.impl.ProfileServiceImpl;
import ru.mail.krivonos.project_jd1.services.impl.UserServiceImpl;
import ru.mail.krivonos.project_jd1.services.model.ProfileDTO;
import ru.mail.krivonos.project_jd1.services.model.user.UserInfoDTO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ProfileUpdateCommand implements Command {

    private UserService userService = UserServiceImpl.getInstance();

    private ProfileService profileService = ProfileServiceImpl.getInstance();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        String email = req.getParameter("email");
        UserInfoDTO userInfoDTO = new UserInfoDTO();
        userInfoDTO.setName(name);
        userInfoDTO.setSurname(surname);
        userService.updateInfo(userInfoDTO);

        String address = req.getParameter("address");
        String telephone = req.getParameter("telephone");
        ProfileDTO profileDTO = new ProfileDTO();
        profileDTO.setUser(userInfoDTO);
        profileDTO.setAddress(address);
        profileDTO.setTelephone(telephone);
        profileService.update(profileDTO);

        String oldPassword = req.getParameter("old-password");
        String newPassword = req.getParameter("new-password");
        if (oldPassword != null && newPassword != null) {
            try {
                userService.updatePassword(email, oldPassword, newPassword);
            } catch (PasswordChangeException e) {
                req.setAttribute("error", "Invalid old password!");
            }
        }
        req.setAttribute("profile", profileDTO);
        return ConfigurationManagerImpl.getInstance().getProperty(PropertiesVariables.PROFILE_PAGE_PATH);
    }
}
