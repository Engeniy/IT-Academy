package ru.mail.krivonos.project_jd1.servlets.command;

import ru.mail.krivonos.project_jd1.services.OrderService;
import ru.mail.krivonos.project_jd1.services.impl.OrderServiceImpl;
import ru.mail.krivonos.project_jd1.servlets.model.CommandEnum;
import ru.mail.krivonos.project_jd1.servlets.model.Constants;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UpdateStateCommand implements Command {

    private OrderService orderService = OrderServiceImpl.getInstance();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        Long id = Long.parseLong(req.getParameter("id"));
        String state = req.getParameter("state");
        orderService.update(id, state);
        resp.sendRedirect(req.getContextPath() + Constants.DEFAULT_URL + CommandEnum.ORDERS.name().toLowerCase() +
                Constants.MESSAGE_POSTFIX + "State changed!");
        return null;
    }
}
