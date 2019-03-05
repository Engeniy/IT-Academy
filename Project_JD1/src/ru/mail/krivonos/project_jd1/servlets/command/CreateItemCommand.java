package ru.mail.krivonos.project_jd1.servlets.command;

import ru.mail.krivonos.project_jd1.config.ConfigurationManagerImpl;
import ru.mail.krivonos.project_jd1.config.PropertiesVariables;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CreateItemCommand implements Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        return ConfigurationManagerImpl.getInstance().getProperty(PropertiesVariables.CREATE_ITEM_PAGE_PATH);
    }
}
