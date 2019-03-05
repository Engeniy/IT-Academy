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
        String message = req.getParameter("message");
        if (message != null) {
            req.setAttribute("message", message);
        }
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
        PermissionsEnum permission = authorizedUser.getPermission();
        switch (permission) {
            case CUSTOMER_PERMISSION:
                Integer userPages = orderService.countPagesForUser(authorizedUser.getId());
                req.setAttribute("pages", userPages);
                if (pageNumber > userPages) {
                    pageNumber = userPages;
                }
                List<OrderForCustomerDTO> userOrders = orderService.getAllForUser(authorizedUser.getId(), pageNumber);
                if (userOrders.isEmpty()) {
                    req.setAttribute("error", "You have no orders!");
                }
                req.setAttribute("orders", userOrders);
                return ConfigurationManagerImpl.getInstance().getProperty(PropertiesVariables.ORDERS_PAGE_PATH);
            case SALE_PERMISSION:
                Integer pages = orderService.countPages();
                req.setAttribute("pages", pages);
                if (pageNumber > pages) {
                    pageNumber = pages;
                }
                List<OrderForSaleDTO> orders = orderService.getAll(pageNumber);
                if (orders.isEmpty()) {
                    req.setAttribute("error", "There is no orders!");
                }
                req.setAttribute("orders", orders);
                return ConfigurationManagerImpl.getInstance().getProperty(PropertiesVariables.ORDERS_FOR_SALE_PAGE_PATH);
                default:
                    session.removeAttribute(Constants.SESSION_USER_KEY);
                    return ConfigurationManagerImpl.getInstance().getProperty(PropertiesVariables.LOGIN_PAGE_PATH);
        }
    }
}

