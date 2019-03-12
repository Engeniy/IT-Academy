package ru.mail.krivonos.project_jd1.servlets.validators.impl;

import org.xml.sax.SAXException;
import ru.mail.krivonos.project_jd1.servlets.validators.XMLValidator;

import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class XMLValidatorImpl implements XMLValidator {

    private static XMLValidator instance;

    private XMLValidatorImpl() {
    }

    public static XMLValidator getInstance() {
        if (instance == null) {
            instance = new XMLValidatorImpl();
        }
        return instance;
    }

    @Override
    public boolean validate(File schemaFile, InputStream fileContent) {
        Source xmlSource = new StreamSource(fileContent);
        SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        try {
            Schema schema = schemaFactory.newSchema(schemaFile);
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
