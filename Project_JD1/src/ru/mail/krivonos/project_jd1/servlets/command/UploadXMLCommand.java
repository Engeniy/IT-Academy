package ru.mail.krivonos.project_jd1.servlets.command;

import ru.mail.krivonos.project_jd1.config.ConfigurationManagerImpl;
import ru.mail.krivonos.project_jd1.config.PropertiesVariables;
import ru.mail.krivonos.project_jd1.repository.model.PermissionsEnum;
import ru.mail.krivonos.project_jd1.services.ItemService;
import ru.mail.krivonos.project_jd1.services.JAXBParserService;
import ru.mail.krivonos.project_jd1.services.impl.ItemServiceImpl;
import ru.mail.krivonos.project_jd1.services.impl.xml.JAXBParserServiceImpl;
import ru.mail.krivonos.project_jd1.services.model.item.ItemDTO;
import ru.mail.krivonos.project_jd1.services.model.user.AuthorizedUserDTO;
import ru.mail.krivonos.project_jd1.services.model.xml.XMLItemDTO;
import ru.mail.krivonos.project_jd1.servlets.model.Constants;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import java.io.*;
import java.nio.file.Paths;
import java.util.List;

public class UploadXMLCommand implements Command {

    private ItemService itemService = ItemServiceImpl.getInstance();

    private JAXBParserService jaxbParserService = JAXBParserServiceImpl.getInstance();

    private static final String UPLOAD_DIR =
            "/home/alex/Documents/IT-Academy/Project_JD1/web/resources/upload/temp.xml";

    private static final String XSD_PATH =
            "/home/alex/Documents/IT-Academy/Project_JD1/web/resources/upload/itemschema.xsd";

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        Part filePart = req.getPart("file");
        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
        try (InputStream fileContent = filePart.getInputStream(); OutputStream os = new FileOutputStream(UPLOAD_DIR)) {
            int input;
            while ((input = fileContent.read()) != -1) {
                os.write(input);
            }
            os.flush();
        }
        File schema = new File(XSD_PATH);
        File inputFile = new File(UPLOAD_DIR);
        if (jaxbParserService.validate(schema, inputFile)) {
            List<XMLItemDTO> xmlItems = jaxbParserService.parse(inputFile);
            itemService.addItems(xmlItems);
        }
        inputFile.delete();
        HttpSession session = req.getSession();
        AuthorizedUserDTO authorizedUser = (AuthorizedUserDTO) session.getAttribute(Constants.SESSION_USER_KEY);
        PermissionsEnum permission = authorizedUser.getRole().getPermissions().get(0);
        List<ItemDTO> items = itemService.getAll(1);
        req.setAttribute("items", items);
        Integer pages = itemService.countPages();
        req.setAttribute("pages", pages);
        if (permission.equals(PermissionsEnum.CUSTOMER_PERMISSION)) {
            return ConfigurationManagerImpl.getInstance().getProperty(PropertiesVariables.ITEMS_PAGE_PATH);
        } else return ConfigurationManagerImpl.getInstance().getProperty(PropertiesVariables.ITEMS_FOR_SALE_PAGE_PATH);
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
