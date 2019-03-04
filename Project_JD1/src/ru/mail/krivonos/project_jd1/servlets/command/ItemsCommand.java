package ru.mail.krivonos.project_jd1.servlets.command;

import ru.mail.krivonos.project_jd1.config.ConfigurationManagerImpl;
import ru.mail.krivonos.project_jd1.config.PropertiesVariables;
import ru.mail.krivonos.project_jd1.repository.model.PermissionsEnum;
import ru.mail.krivonos.project_jd1.services.ItemService;
import ru.mail.krivonos.project_jd1.services.impl.ItemServiceImpl;
import ru.mail.krivonos.project_jd1.services.model.item.ItemDTO;
import ru.mail.krivonos.project_jd1.services.model.user.AuthorizedUserDTO;
import ru.mail.krivonos.project_jd1.servlets.model.Constants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class ItemsCommand implements Command {

    private ItemService itemService = ItemServiceImpl.getInstance();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        String page = req.getParameter("page");
        Integer pageNumber;
        if (page != null) {
            pageNumber = Integer.parseInt(page);
            if (pageNumber == 0) {
                pageNumber = 1;
            }
        } else {
            pageNumber = 1;
        }
        HttpSession session = req.getSession();
        AuthorizedUserDTO authorizedUser = (AuthorizedUserDTO) session.getAttribute(Constants.SESSION_USER_KEY);
        PermissionsEnum permission = authorizedUser.getRole().getPermissions().get(0);
        List<ItemDTO> items = itemService.getAll(pageNumber);
        req.setAttribute("items", items);
        Integer pages = itemService.countPages();
        req.setAttribute("pages", pages);
        if (permission.equals(PermissionsEnum.CUSTOMER_PERMISSION)) {
            return ConfigurationManagerImpl.getInstance().getProperty(PropertiesVariables.ITEMS_PAGE_PATH);
        } else return ConfigurationManagerImpl.getInstance().getProperty(PropertiesVariables.ITEMS_FOR_SALE_PAGE_PATH);

    }
}
