package ru.mail.krivonos.project_jd1.servlets.command;

import ru.mail.krivonos.project_jd1.config.ConfigurationManagerImpl;
import ru.mail.krivonos.project_jd1.config.PropertiesVariables;
import ru.mail.krivonos.project_jd1.repository.model.PermissionsEnum;
import ru.mail.krivonos.project_jd1.services.OrderService;
import ru.mail.krivonos.project_jd1.services.impl.OrderServiceImpl;
import ru.mail.krivonos.project_jd1.services.model.order.OrderForCustomerDTO;
import ru.mail.krivonos.project_jd1.services.model.order.OrderForSaleDTO;
import ru.mail.krivonos.project_jd1.services.model.user.AuthorizedUserDTO;
import ru.mail.krivonos.project_jd1.servlets.model.Constants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class OrdersCommand implements Command {

    private OrderService orderService = OrderServiceImpl.getInstance();

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
        if (permission.equals(PermissionsEnum.CUSTOMER_PERMISSION)) {
            List<OrderForCustomerDTO> orders = orderService.getAllForUser(authorizedUser.getId(), pageNumber);
            req.setAttribute("orders", orders);
            Integer pages = orderService.countPagesForUser(authorizedUser.getId());
            req.setAttribute("pages", pages);
            return ConfigurationManagerImpl.getInstance().getProperty(PropertiesVariables.ORDERS_PAGE_PATH);
        } else {
            List<OrderForSaleDTO> orders = orderService.getAll(pageNumber);
            req.setAttribute("orders", orders);
            Integer pages = orderService.countPages();
            req.setAttribute("pages", pages);
            return ConfigurationManagerImpl.getInstance().getProperty(PropertiesVariables.ORDERS_FOR_SALE_PAGE_PATH);
        }
    }
}

