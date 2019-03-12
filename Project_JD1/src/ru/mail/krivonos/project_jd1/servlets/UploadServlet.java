package ru.mail.krivonos.project_jd1.servlets;

import ru.mail.krivonos.project_jd1.services.ItemService;
import ru.mail.krivonos.project_jd1.services.ItemsUploadService;
import ru.mail.krivonos.project_jd1.services.exceptions.ItemUniqueNumberException;
import ru.mail.krivonos.project_jd1.services.impl.ItemServiceImpl;
import ru.mail.krivonos.project_jd1.services.impl.xml.ItemsUploadServiceImpl;
import ru.mail.krivonos.project_jd1.services.model.xml.XMLItemDTO;
import ru.mail.krivonos.project_jd1.servlets.model.CommandEnum;
import ru.mail.krivonos.project_jd1.servlets.model.Constants;
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
import java.util.List;

@MultipartConfig
public class UploadServlet extends HttpServlet {

    private ItemService itemService = ItemServiceImpl.getInstance();

    private ItemsUploadService itemsUploadService = ItemsUploadServiceImpl.getInstance();

    private XMLValidator xmlValidator = XMLValidatorImpl.getInstance();

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
        Part filePart = req.getPart("file");
        String schemaPath = req.getServletContext().getRealPath(Constants.XSD_PATH);
        try (InputStream fileContent = filePart.getInputStream()) {
            File schema = new File(schemaPath);
            if (xmlValidator.validate(schema, fileContent)) {
                try (InputStream fileContent2 = filePart.getInputStream()) {
                    List<XMLItemDTO> xmlItems = itemsUploadService.parse(fileContent2);
                    try {
                        itemService.addItems(xmlItems);
                    } catch (ItemUniqueNumberException e) {
                        req.setAttribute("error", "Adding denied! One or more items has the same unique number!");
                        req.getRequestDispatcher(Constants.DEFAULT_URL + CommandEnum.UPLOAD.name()
                                .toLowerCase()).forward(req, resp);
                    }
                }
            } else {
                req.setAttribute("error", "You file is not valid!");
                req.getRequestDispatcher(Constants.DEFAULT_URL + CommandEnum.UPLOAD.name()
                        .toLowerCase()).forward(req, resp);
            }
        }
        req.setAttribute("message", "Items added!");
        req.getRequestDispatcher(Constants.DEFAULT_URL + CommandEnum.ITEMS.name().toLowerCase()).forward(req, resp);

    }
}
