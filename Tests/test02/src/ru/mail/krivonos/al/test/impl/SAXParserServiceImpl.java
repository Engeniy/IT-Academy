package ru.mail.krivonos.al.test.impl;

import org.xml.sax.SAXException;
import ru.mail.krivonos.al.test.XMLParserService;
import ru.mail.krivonos.al.test.handler.PurchaseOrderHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;

public class SAXParserServiceImpl implements XMLParserService {

    private static XMLParserService instance;

    private SAXParserServiceImpl() {
    }

    @Override
    public String parse(File file) {
        String result = null;
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            PurchaseOrderHandler handler = new PurchaseOrderHandler();
            saxParser.parse(file, handler);
            result = handler.getResult();
        } catch (ParserConfigurationException | SAXException | IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return result;
    }

    public static XMLParserService getInstance() {
        if (instance == null) {
            instance = new SAXParserServiceImpl();
        }
        return instance;
    }
}
