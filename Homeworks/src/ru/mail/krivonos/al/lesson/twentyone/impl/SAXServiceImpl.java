package ru.mail.krivonos.al.lesson.twentyone.impl;

import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import ru.mail.krivonos.al.lesson.twentyone.SAXService;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;

public class SAXServiceImpl implements SAXService {

    private static SAXService instance;

    @Override
    public void parseFile(File file, DefaultHandler handler) {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            saxParser.parse(file, handler);
        } catch (SAXException | ParserConfigurationException | IOException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    private SAXServiceImpl() {
    }

    public static SAXService getInstance() {
        if (instance == null) {
            instance = new SAXServiceImpl();
        }
        return instance;
    }
}
