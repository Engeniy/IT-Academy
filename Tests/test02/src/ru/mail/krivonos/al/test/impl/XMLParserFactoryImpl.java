package ru.mail.krivonos.al.test.impl;

import org.xml.sax.SAXException;
import ru.mail.krivonos.al.test.XMLParserFactory;
import ru.mail.krivonos.al.test.XMLParserService;
import ru.mail.krivonos.al.test.model.ParserType;

import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;

public class XMLParserFactoryImpl implements XMLParserFactory {

    private static XMLParserFactory instance;

    private XMLParserFactoryImpl() {
    }

    @Override
    public XMLParserService getParser(ParserType type) {
        switch (type) {
            case DOM:
                return DOMParserServiceImpl.getInstance();
            case SAX:
                return SAXParserServiceImpl.getInstance();
            case StAX:
                return StAXParserServiceImpl.getInstance();
            case JAXB:
                return JAXBParserServiceImpl.getInstance();
            default:
                throw new UnsupportedOperationException("Unsupported enum type!");
        }
    }

    @Override
    public void validate(String xsdFileName, String xmlFileName) {
        File schemaFile = new File(xsdFileName);
        Source xmlFile = new StreamSource(new File(xmlFileName));
        SchemaFactory schemaFactory = SchemaFactory
                .newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        try {
            Schema schema = schemaFactory.newSchema(schemaFile);
            Validator validator = schema.newValidator();
            validator.validate(xmlFile);
            System.out.println(xmlFile.getSystemId() + " is valid");
        } catch (SAXException e) {
            System.out.println(xmlFile.getSystemId() + " is NOT valid reason:" + e);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public static XMLParserFactory getInstance() {
        if (instance == null) {
            instance = new XMLParserFactoryImpl();
        }
        return instance;
    }
}
