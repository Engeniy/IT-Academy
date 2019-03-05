package ru.mail.krivonos.project_jd1.servlets.command;

import ru.mail.krivonos.project_jd1.config.ConfigurationManagerImpl;
import ru.mail.krivonos.project_jd1.config.PropertiesVariables;
import ru.mail.krivonos.project_jd1.services.ProfileService;
import ru.mail.krivonos.project_jd1.services.impl.ProfileServiceImpl;
import ru.mail.krivonos.project_jd1.services.model.ProfileDTO;
import ru.mail.krivonos.project_jd1.services.model.user.AuthorizedUserDTO;
import ru.mail.krivonos.project_jd1.servlets.model.Constants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ProfileCommand implements Command {

    private ProfileService profileService = ProfileServiceImpl.getInstance();


    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();
        AuthorizedUserDTO authorizedUser = (AuthorizedUserDTO) session.getAttribute(Constants.SESSION_USER_KEY);
        ProfileDTO profileDTO = profileService.getByUserID(authorizedUser.getId());
        if (profileDTO != null) {
            req.setAttribute("profile", profileDTO);
            return ConfigurationManagerImpl.getInstance().getProperty(PropertiesVariables.PROFILE_PAGE_PATH);
        } else {
            session.removeAttribute(Constants.SESSION_USER_KEY);
            return ConfigurationManagerImpl.getInstance().getProperty(PropertiesVariables.LOGIN_PAGE_PATH);
        }
    }
}
