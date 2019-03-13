package ru.mail.krivonos.project_jd1.servlets;

import ru.mail.krivonos.project_jd1.services.ItemService;
import ru.mail.krivonos.project_jd1.services.ItemsUploadService;
import ru.mail.krivonos.project_jd1.services.exceptions.ItemUniqueNumberException;
import ru.mail.krivonos.project_jd1.services.impl.ItemServiceImpl;
import ru.mail.krivonos.project_jd1.services.impl.xml.ItemsUploadServiceImpl;
import ru.mail.krivonos.project_jd1.services.model.xml.XMLItemDTO;
import ru.mail.krivonos.project_jd1.servlets.model.CommandEnum;
import ru.mail.krivonos.project_jd1.servlets.constants.ServletConstants;
import ru.mail.krivonos.project_jd1.servlets.validators.XMLItemsDTOValidator;
import ru.mail.krivonos.project_jd1.servlets.validators.impl.XMLItemsDTOValidatorImpl;
import ru.mail.krivonos.project_jd1.servlets.validators.XMLValidator;
import ru.mail.krivonos.project_jd1.servlets.validators.impl.XMLValidatorImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@MultipartConfig
public class UploadServlet extends HttpServlet {

    private ItemService itemService = ItemServiceImpl.getInstance();

    private ItemsUploadService itemsUploadService = ItemsUploadServiceImpl.getInstance();

    private XMLValidator xmlValidator = XMLValidatorImpl.getInstance();

    private XMLItemsDTOValidator xmlItemsDTOValidator = XMLItemsDTOValidatorImpl.getInstance();

    @Override
    public void init() {
        System.out.println("------- UploadServlet initialization -------");
    }

    @Override
    public void destroy() {
        System.out.println("------- UploadServlet destroy -------");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, String> messages = new HashMap<>();
        req.setAttribute("messages", messages);
        Part filePart = req.getPart("file");
        String schemaPath = req.getServletContext().getRealPath(ServletConstants.XSD_PATH);
        try (InputStream forValidation = filePart.getInputStream()) {
            File schema = new File(schemaPath);
            if (xmlValidator.validate(schema, forValidation)) {
                try (InputStream fileContent = filePart.getInputStream()) {
                    List<XMLItemDTO> xmlItems = itemsUploadService.parse(fileContent);
                    xmlItemsDTOValidator.validate(messages, xmlItems);
                    if (!messages.isEmpty()) {
                        req.getRequestDispatcher(ServletConstants.DEFAULT_URL + CommandEnum.UPLOAD.name()
                                .toLowerCase()).forward(req, resp);
                    } else {
                        try {
                            itemService.addItems(xmlItems);
                            req.setAttribute("message", "Items added!");
                            req.getRequestDispatcher(ServletConstants.DEFAULT_URL + CommandEnum.ITEMS.name().toLowerCase())
                                    .forward(req, resp);
                        } catch (ItemUniqueNumberException e) {
                            messages.put("match",  "Adding denied! One or more items has the same unique number!");
                            req.getRequestDispatcher(ServletConstants.DEFAULT_URL + CommandEnum.UPLOAD.name()
                                    .toLowerCase()).forward(req, resp);
                        }
                    }
                }
            } else {
                req.setAttribute("error", "You file is not valid!");
                req.getRequestDispatcher(ServletConstants.DEFAULT_URL + CommandEnum.UPLOAD.name()
                        .toLowerCase()).forward(req, resp);
            }
        }
    }
}
