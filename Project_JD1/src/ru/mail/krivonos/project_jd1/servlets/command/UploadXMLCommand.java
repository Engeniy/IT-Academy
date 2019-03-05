package ru.mail.krivonos.project_jd1.servlets.command;

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
            "/home/alex/Documents/IT-Academy/Project_JD1/web/resources/upload/itemschema.xsd";

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String error = null;
        Part filePart = req.getPart("file");
        File inputFile = itemsUploadService.uploadFile(filePart, UPLOAD_DIR);
        File schema = new File(XSD_PATH);
        if (itemsUploadService.validate(schema, inputFile)) {
            List<XMLItemDTO> xmlItems = itemsUploadService.parse(inputFile);
            try {
                itemService.addItems(xmlItems);
            } catch (ItemUniqueNumberException e) {
                resp.sendRedirect(req.getContextPath() + Constants.DEFAULT_URL + CommandEnum.ITEMS.name().toLowerCase() +
                        Constants.ERROR_POSTFIX + "Adding_denied!One_of_the_items_has_the_same_unique_number!");
                return null;
            } finally {
                inputFile.delete();
            }
        }
        resp.sendRedirect(req.getContextPath() + Constants.DEFAULT_URL + CommandEnum.ITEMS.name().toLowerCase() +
                Constants.MESSAGE_POSTFIX + "Items_added!");
        return null;
    }

    private String extractFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");
        for (String s : items) {
            if (s.trim().startsWith("filename")) {
                return s.substring(s.indexOf("=") + 2, s.length() - 1);
            }
        }
        return "";
    }
}
