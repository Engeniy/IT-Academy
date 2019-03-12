package ru.mail.krivonos.project_jd1.servlets.commands.impl;

import ru.mail.krivonos.project_jd1.config.ConfigurationManagerImpl;
import ru.mail.krivonos.project_jd1.config.PropertiesVariables;
import ru.mail.krivonos.project_jd1.servlets.commands.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginRedirectCommand implements Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        return ConfigurationManagerImpl.getInstance().getProperty(PropertiesVariables.LOGIN_PAGE_PATH);
    }
}
