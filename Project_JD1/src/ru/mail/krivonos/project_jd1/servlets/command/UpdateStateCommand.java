package ru.mail.krivonos.project_jd1.servlets.command;

import ru.mail.krivonos.project_jd1.config.ConfigurationManagerImpl;
import ru.mail.krivonos.project_jd1.config.PropertiesVariables;
import ru.mail.krivonos.project_jd1.services.OrderService;
import ru.mail.krivonos.project_jd1.services.impl.OrderServiceImpl;
import ru.mail.krivonos.project_jd1.services.model.order.OrderForSaleDTO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class UpdateStateCommand implements Command {

    private OrderService orderService = OrderServiceImpl.getInstance();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        Long id = Long.parseLong(req.getParameter("id"));
        String state = req.getParameter("state");
        orderService.update(id, state);
        List<OrderForSaleDTO> orders = orderService.getAll(1);
        req.setAttribute("orders", orders);
        Integer pages = orderService.countPages();
        req.setAttribute("pages", pages);
        return ConfigurationManagerImpl.getInstance().getProperty(PropertiesVariables.ORDERS_FOR_SALE_PAGE_PATH);
    }
}
