package ru.mail.krivonos.project_jd1.servlets.commands.impl;

import ru.mail.krivonos.project_jd1.config.ConfigurationManagerImpl;
import ru.mail.krivonos.project_jd1.config.PropertiesVariables;
import ru.mail.krivonos.project_jd1.repository.model.OrderState;
import ru.mail.krivonos.project_jd1.services.OrderService;
import ru.mail.krivonos.project_jd1.services.impl.OrderServiceImpl;
import ru.mail.krivonos.project_jd1.services.model.order.OrderForSaleDTO;
import ru.mail.krivonos.project_jd1.servlets.commands.Command;
import ru.mail.krivonos.project_jd1.servlets.model.CommandEnum;
import ru.mail.krivonos.project_jd1.servlets.constants.ServletConstants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ChooseStateCommand implements Command {

    private OrderService orderService = OrderServiceImpl.getInstance();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String state = req.getParameter("filter-state");
        if (state.equals("ALL")) {
            resp.sendRedirect(req.getContextPath() + ServletConstants.DEFAULT_URL + CommandEnum.ORDERS.name().toLowerCase());
            return null;
        }
        req.setAttribute("states", OrderState.values());
        req.setAttribute("currentState", state);
        List<OrderForSaleDTO> orders = orderService.getAllByOrderState(1, state);
        if (orders.isEmpty()) {
            req.setAttribute("error", "There is no orders for chosen state!");
        } else {
            req.setAttribute("orders", orders);
            Integer pages = orderService.countPagesForState(state);
            req.setAttribute("pages", pages);
        }
        return ConfigurationManagerImpl.getInstance().getProperty(PropertiesVariables.ORDERS_FOR_SALE_PAGE_PATH);
    }
}
