package ru.mail.krivonos.project_jd1.servlets.command;

import ru.mail.krivonos.project_jd1.config.ConfigurationManagerImpl;
import ru.mail.krivonos.project_jd1.config.PropertiesVariables;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegistrationRedirectCommand implements Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        return ConfigurationManagerImpl.getInstance().getProperty(PropertiesVariables.REGISTRATION_PAGE_PATH);
    }
}
