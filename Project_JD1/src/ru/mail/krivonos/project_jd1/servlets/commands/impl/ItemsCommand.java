package ru.mail.krivonos.project_jd1.servlets.commands.impl;

import ru.mail.krivonos.project_jd1.config.ConfigurationManagerImpl;
import ru.mail.krivonos.project_jd1.config.PropertiesVariables;
import ru.mail.krivonos.project_jd1.repository.model.PermissionsEnum;
import ru.mail.krivonos.project_jd1.services.ItemService;
import ru.mail.krivonos.project_jd1.services.impl.ItemServiceImpl;
import ru.mail.krivonos.project_jd1.services.model.item.ItemDTO;
import ru.mail.krivonos.project_jd1.services.model.user.AuthorizedUserDTO;
import ru.mail.krivonos.project_jd1.servlets.commands.Command;
import ru.mail.krivonos.project_jd1.servlets.model.Constants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class ItemsCommand implements Command {

    private ItemService itemService = ItemServiceImpl.getInstance();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        String message = req.getParameter("message");
        if (message != null) {
            req.setAttribute("message", message);
        }
        Integer pages = itemService.countPages();
        req.setAttribute("pages", pages);
        String page = req.getParameter("page");
        Integer pageNumber;
        if (page != null) {
            pageNumber = Integer.parseInt(page);
            if (pageNumber == 0) {
                pageNumber = 1;
            } else if (pageNumber > pages) {
                pageNumber = pages;
            }
        } else {
            pageNumber = 1;
        }
        List<ItemDTO> items = itemService.getAll(pageNumber);
        if (items.isEmpty()) {
            req.setAttribute("error", "There is no items!");
        }
        req.setAttribute("items", items);
        HttpSession session = req.getSession();
        AuthorizedUserDTO authorizedUser = (AuthorizedUserDTO) session.getAttribute(Constants.SESSION_USER_KEY);
        PermissionsEnum permission = authorizedUser.getPermission();
        switch (permission) {
            case CUSTOMER_PERMISSION:
                return ConfigurationManagerImpl.getInstance().getProperty(PropertiesVariables.ITEMS_PAGE_PATH);
            case SALE_PERMISSION:
                return ConfigurationManagerImpl.getInstance().getProperty(PropertiesVariables.ITEMS_FOR_SALE_PAGE_PATH);
            default:
                session.removeAttribute(Constants.SESSION_USER_KEY);
                return ConfigurationManagerImpl.getInstance().getProperty(PropertiesVariables.LOGIN_PAGE_PATH);
        }
    }
}
