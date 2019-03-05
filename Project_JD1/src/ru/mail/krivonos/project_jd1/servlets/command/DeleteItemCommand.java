package ru.mail.krivonos.project_jd1.servlets.command;

import ru.mail.krivonos.project_jd1.services.ItemService;
import ru.mail.krivonos.project_jd1.services.impl.ItemServiceImpl;
import ru.mail.krivonos.project_jd1.servlets.model.CommandEnum;
import ru.mail.krivonos.project_jd1.servlets.model.Constants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteItemCommand implements Command {

    private ItemService itemService = ItemServiceImpl.getInstance();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        itemService.deleteByID(Long.parseLong(req.getParameter("item_id")));
        req.setAttribute("message", "Item deleted!");
        resp.sendRedirect(req.getContextPath() + Constants.DEFAULT_URL + CommandEnum.ITEMS.name() +
                Constants.MESSAGE_POSTFIX + "Item_deleted!");
        return null;
    }
}
