package ru.mail.krivonos.project_jd1.servlets.commands.impl;

import ru.mail.krivonos.project_jd1.services.OrderService;
import ru.mail.krivonos.project_jd1.services.impl.OrderServiceImpl;
import ru.mail.krivonos.project_jd1.services.model.order.CreatedOrderDTO;
import ru.mail.krivonos.project_jd1.services.model.user.AuthorizedUserDTO;
import ru.mail.krivonos.project_jd1.servlets.commands.Command;
import ru.mail.krivonos.project_jd1.servlets.model.CommandEnum;
import ru.mail.krivonos.project_jd1.servlets.constants.ServletConstants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class OrderCommand implements Command {

    private OrderService orderService = OrderServiceImpl.getInstance();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Long item_id = Long.parseLong(req.getParameter("item_id"));
        Integer quantity = Integer.parseInt(req.getParameter("quantity"));
        if (quantity <= 0) {
            resp.sendRedirect(req.getContextPath() + ServletConstants.DEFAULT_URL + CommandEnum.ITEMS.name().toLowerCase() +
                    ServletConstants.ERROR_POSTFIX + "Quantity must be greater than zero!");
            return null;
        }
        CreatedOrderDTO createdOrderDTO = new CreatedOrderDTO();
        createdOrderDTO.setItemID(item_id);
        createdOrderDTO.setQuantity(quantity);
        HttpSession session = req.getSession();
        AuthorizedUserDTO authorizedUser = (AuthorizedUserDTO) session.getAttribute(ServletConstants.SESSION_USER_KEY);
        createdOrderDTO.setUserID(authorizedUser.getId());
        orderService.add(createdOrderDTO);
        resp.sendRedirect(req.getContextPath() + ServletConstants.DEFAULT_URL + CommandEnum.ITEMS.name().toLowerCase() +
                ServletConstants.MESSAGE_POSTFIX + "Order created!");
        return null;

    }
}
