package ru.mail.krivonos.project_jd1.services.impl.xml;

import org.xml.sax.SAXException;
import ru.mail.krivonos.project_jd1.services.JAXBParserService;
import ru.mail.krivonos.project_jd1.services.model.xml.XMLItemDTO;
import ru.mail.krivonos.project_jd1.services.model.xml.XMLItemsDTO;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class JAXBParserServiceImpl implements JAXBParserService {

    private static JAXBParserService instance;

    private JAXBParserServiceImpl() {
    }

    public static JAXBParserService getInstance() {
        if (instance == null) {
            instance = new JAXBParserServiceImpl();
        }
        return instance;
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
