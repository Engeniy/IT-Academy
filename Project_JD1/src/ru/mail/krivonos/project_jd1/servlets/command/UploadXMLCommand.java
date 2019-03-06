package ru.mail.krivonos.project_jd1.servlets.command;

import ru.mail.krivonos.project_jd1.config.ConfigurationManagerImpl;
import ru.mail.krivonos.project_jd1.config.PropertiesVariables;
import ru.mail.krivonos.project_jd1.services.ItemService;
import ru.mail.krivonos.project_jd1.services.ItemsUploadService;
import ru.mail.krivonos.project_jd1.services.exceptions.ItemUniqueNumberException;
import ru.mail.krivonos.project_jd1.services.impl.ItemServiceImpl;
import ru.mail.krivonos.project_jd1.services.impl.xml.ItemsUploadServiceImpl;
import ru.mail.krivonos.project_jd1.services.model.xml.XMLItemDTO;
import ru.mail.krivonos.project_jd1.servlets.model.CommandEnum;
import ru.mail.krivonos.project_jd1.servlets.model.Constants;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class UploadXMLCommand implements Command {

    private ItemService itemService = ItemServiceImpl.getInstance();

    private ItemsUploadService itemsUploadService = ItemsUploadServiceImpl.getInstance();

    private static final String UPLOAD_DIR =
            "/home/alex/Documents/IT-Academy/Project_JD1/web/resources/upload/temp.xml";

    private static final String XSD_PATH =
            "/home/alex/Documents/IT-Academy/Project_JD1/src/ru/mail/krivonos/project_jd1/resources/itemschema.xsd";

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String error = null;
        Part filePart = req.getPart("file");
        File inputFile = itemsUploadService.uploadFile(filePart, UPLOAD_DIR);
        File schema = new File(XSD_PATH);
        if (itemsUploadService.isValid(schema, inputFile)) {
            List<XMLItemDTO> xmlItems = itemsUploadService.parse(inputFile);
            try {
                itemService.addItems(xmlItems);
            } catch (ItemUniqueNumberException e) {
                req.setAttribute("error", "Adding denied! One or more items has the same unique number!");
                return ConfigurationManagerImpl.getInstance().getProperty(PropertiesVariables.UPLOAD_PAGE_PATH);
            } finally {
                inputFile.delete();
            }
        } else {
            req.setAttribute("error", "You file is not valid!");
            return ConfigurationManagerImpl.getInstance().getProperty(PropertiesVariables.UPLOAD_PAGE_PATH);
        }
        resp.sendRedirect(req.getContextPath() + Constants.DEFAULT_URL + CommandEnum.ITEMS.name().toLowerCase() +
                Constants.MESSAGE_POSTFIX + "Items added!");
        return null;
    }
}
