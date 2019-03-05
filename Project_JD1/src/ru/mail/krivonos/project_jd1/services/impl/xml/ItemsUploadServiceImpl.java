package ru.mail.krivonos.project_jd1.services.impl.xml;

import org.xml.sax.SAXException;
import ru.mail.krivonos.project_jd1.services.ItemsUploadService;
import ru.mail.krivonos.project_jd1.services.model.xml.XMLItemDTO;
import ru.mail.krivonos.project_jd1.services.model.xml.XMLItemsDTO;

import javax.servlet.http.Part;
import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.*;
import java.nio.file.Paths;
import java.util.List;

public class ItemsUploadServiceImpl implements ItemsUploadService {

    private static ItemsUploadService instance;

    private ItemsUploadServiceImpl() {
    }

    public static ItemsUploadService getInstance() {
        if (instance == null) {
            instance = new ItemsUploadServiceImpl();
        }
        return instance;
    }

    @Override
    public File uploadFile(Part part, String tempFileName) throws IOException {
        String fileName = Paths.get(part.getSubmittedFileName()).getFileName().toString();
        File tempFile = new File(tempFileName);
        if (!tempFile.exists()) {
           if (!tempFile.createNewFile()) {
               throw new RuntimeException("Can't create temp file!");
           }
        }
        try (InputStream fileContent = part.getInputStream(); OutputStream os = new FileOutputStream(tempFile)) {
            int input;
            while ((input = fileContent.read()) != -1) {
                os.write(input);
            }
            os.flush();
        }
        return tempFile;
    }

    @Override
    public List<XMLItemDTO> parse(File file) {
        try {
            JAXBContext context = JAXBContext.newInstance(XMLItemsDTO.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            XMLItemsDTO itemsDTO = (XMLItemsDTO) unmarshaller.unmarshal(file);
            return itemsDTO.getXmlItemDTOList();
        } catch (JAXBException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean validate(File xsdFile, File xmlFile) {
        Source xmlSource = new StreamSource(xmlFile);
        SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        try {
            Schema schema = schemaFactory.newSchema(xsdFile);
            Validator validator = schema.newValidator();
            validator.validate(xmlSource);
            return true;
        } catch (SAXException e) {
            return false;
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
