package ru.mail.krivonos.project_jd1.servlets.commands.impl;

import ru.mail.krivonos.project_jd1.config.ConfigurationManagerImpl;
import ru.mail.krivonos.project_jd1.config.PropertiesVariables;
import ru.mail.krivonos.project_jd1.services.ItemService;
import ru.mail.krivonos.project_jd1.services.exceptions.ItemUniqueNumberException;
import ru.mail.krivonos.project_jd1.services.impl.ItemServiceImpl;
import ru.mail.krivonos.project_jd1.services.model.item.ItemDTO;
import ru.mail.krivonos.project_jd1.servlets.commands.Command;
import ru.mail.krivonos.project_jd1.servlets.model.CommandEnum;
import ru.mail.krivonos.project_jd1.servlets.model.Constants;
import ru.mail.krivonos.project_jd1.servlets.validators.ItemDTOValidator;
import ru.mail.krivonos.project_jd1.servlets.validators.impl.ItemDTOValidatorImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class AddItemCommand implements Command {

    private ItemService itemService = ItemServiceImpl.getInstance();

    private ItemDTOValidator itemDTOValidator = ItemDTOValidatorImpl.getInstance();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Map<String, String> messages = new HashMap<>();
        req.setAttribute("messages", messages);
        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setName(req.getParameter("name"));
        itemDTO.setDescription(req.getParameter("description"));
        itemDTO.setUniqueNumber(req.getParameter("uniqueNumber"));
        itemDTO.setPrice(req.getParameter("price"));
        itemDTOValidator.validate(messages, itemDTO);

        if (!messages.isEmpty()) {
            return ConfigurationManagerImpl.getInstance().getProperty(PropertiesVariables.CREATE_ITEM_PAGE_PATH);
        } else {
            try {
                itemService.add(itemDTO);
            } catch (ItemUniqueNumberException e) {
                messages.put("unique", "Item with the same unique number exists!");
                return ConfigurationManagerImpl.getInstance().getProperty(PropertiesVariables.CREATE_ITEM_PAGE_PATH);
            }
        }
        resp.sendRedirect(req.getContextPath() + Constants.DEFAULT_URL + CommandEnum.ITEMS.name().toLowerCase() +
                Constants.MESSAGE_POSTFIX + "Item added!");
        return null;
    }
}
