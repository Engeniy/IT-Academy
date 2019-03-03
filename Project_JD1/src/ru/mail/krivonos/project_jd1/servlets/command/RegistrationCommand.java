package ru.mail.krivonos.project_jd1.servlets.command;

import ru.mail.krivonos.project_jd1.config.ConfigurationManagerImpl;
import ru.mail.krivonos.project_jd1.config.PropertiesVariables;
import ru.mail.krivonos.project_jd1.services.ProfileService;
import ru.mail.krivonos.project_jd1.services.UserService;
import ru.mail.krivonos.project_jd1.services.impl.ProfileServiceImpl;
import ru.mail.krivonos.project_jd1.services.impl.UserServiceImpl;
import ru.mail.krivonos.project_jd1.services.model.ProfileDTO;
import ru.mail.krivonos.project_jd1.services.model.user.UserRegistrationDTO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegistrationCommand implements Command {

    private UserService userService = UserServiceImpl.getInstance();

    private ProfileService profileService = ProfileServiceImpl.getInstance();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        if (req.getParameter("state") != null) {
            return ConfigurationManagerImpl.getInstance().getProperty(PropertiesVariables.REGISTRATION_PAGE_PATH);
        }
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        UserRegistrationDTO userRegistrationDTO = new UserRegistrationDTO();
        userRegistrationDTO.setName(name);
        userRegistrationDTO.setSurname(surname);
        userRegistrationDTO.setEmail(email);
        userRegistrationDTO.setPassword(password);
        userService.add(userRegistrationDTO);

        String address = req.getParameter("address");
        String telephone = req.getParameter("telephone");
        ProfileDTO profileDTO = new ProfileDTO();
        profileDTO.setAddress(address);
        profileDTO.setTelephone(telephone);
        profileService.add(profileDTO);
        return ConfigurationManagerImpl.getInstance().getProperty(PropertiesVariables.LOGIN_PAGE_PATH);

    }
}
