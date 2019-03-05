package ru.mail.krivonos.project_jd1.servlets.command;

import ru.mail.krivonos.project_jd1.services.ItemService;
import ru.mail.krivonos.project_jd1.services.exceptions.ItemUniqueNumberException;
import ru.mail.krivonos.project_jd1.services.impl.ItemServiceImpl;
import ru.mail.krivonos.project_jd1.services.model.item.ItemDTO;
import ru.mail.krivonos.project_jd1.servlets.model.CommandEnum;
import ru.mail.krivonos.project_jd1.servlets.model.Constants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;

public class AddItemCommand implements Command {

    private ItemService itemService = ItemServiceImpl.getInstance();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setName(req.getParameter("name"));
        itemDTO.setDescription(req.getParameter("description"));
        itemDTO.setUniqueNumber(req.getParameter("uniqueNumber"));
        BigDecimal price = BigDecimal.valueOf(Double.parseDouble(req.getParameter("price")));
        itemDTO.setPrice(price);
        itemDTO.setId(0L);
        try {
            itemService.add(itemDTO);
        } catch (ItemUniqueNumberException e) {
            resp.sendRedirect(req.getContextPath() + Constants.DEFAULT_URL + CommandEnum.ITEMS.name().toLowerCase() +
                    Constants.ERROR_POSTFIX + "Item_with_the_same_unique_number_exists!");
            return null;
        }
        resp.sendRedirect(req.getContextPath() + Constants.DEFAULT_URL + CommandEnum.ITEMS.name().toLowerCase() +
                Constants.MESSAGE_POSTFIX + "Item_added!");
        return null;
    }
}
