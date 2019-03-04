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
import java.math.BigDecimal;
import java.util.List;

public class AddItemCommand implements Command {

    private ItemService itemService = ItemServiceImpl.getInstance();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setName(req.getParameter("name"));
        itemDTO.setDescription(req.getParameter("description"));
        itemDTO.setUniqueNumber(req.getParameter("uniqueNumber"));
        BigDecimal price = BigDecimal.valueOf(Double.parseDouble(req.getParameter("price")));
        itemDTO.setPrice(price);
        itemDTO.setId(0L);
        itemService.add(itemDTO);
        HttpSession session = req.getSession();
        AuthorizedUserDTO authorizedUser = (AuthorizedUserDTO) session.getAttribute(Constants.SESSION_USER_KEY);
        PermissionsEnum permission = authorizedUser.getRole().getPermissions().get(0);
        List<ItemDTO> items = itemService.getAll(1);
        req.setAttribute("items", items);
        Integer pages = itemService.countPages();
        req.setAttribute("pages", pages);
        if (permission.equals(PermissionsEnum.CUSTOMER_PERMISSION)) {
            return ConfigurationManagerImpl.getInstance().getProperty(PropertiesVariables.ITEMS_PAGE_PATH);
        } else return ConfigurationManagerImpl.getInstance().getProperty(PropertiesVariables.ITEMS_FOR_SALE_PAGE_PATH);
    }
}
