package ru.mail.krivonos.project_jd1.servlets.commands.impl;

import ru.mail.krivonos.project_jd1.services.ItemService;
import ru.mail.krivonos.project_jd1.services.impl.ItemServiceImpl;
import ru.mail.krivonos.project_jd1.servlets.commands.Command;
import ru.mail.krivonos.project_jd1.servlets.model.CommandEnum;
import ru.mail.krivonos.project_jd1.servlets.constants.ServletConstants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteItemCommand implements Command {

    private ItemService itemService = ItemServiceImpl.getInstance();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        itemService.deleteByID(Long.parseLong(req.getParameter("item_id")));
        resp.sendRedirect(req.getContextPath() + ServletConstants.DEFAULT_URL + CommandEnum.ITEMS.name().toLowerCase() +
                ServletConstants.MESSAGE_POSTFIX + "Item deleted!");
        return null;
    }
}
