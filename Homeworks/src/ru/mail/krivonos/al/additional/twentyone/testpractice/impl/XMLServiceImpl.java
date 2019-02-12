package ru.mail.krivonos.al.additional.twentyone.testpractice.impl;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import ru.mail.krivonos.al.additional.twentyone.testpractice.XMLService;
import ru.mail.krivonos.al.additional.twentyone.testpractice.handler.ShipOrderHandler;
import ru.mail.krivonos.al.additional.twentyone.testpractice.model.ParserType;
import ru.mail.krivonos.al.additional.twentyone.testpractice.model.ShipItem;
import ru.mail.krivonos.al.additional.twentyone.testpractice.model.ShipOrder;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.*;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class XMLServiceImpl implements XMLService {

    private static XMLService instance;

    private XMLServiceImpl() {
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

    @Override
    public void printItemInfo(ParserType type, String xmlFileName) {
        File targetFile = new File(xmlFileName);
        if (!targetFile.exists()) {
            throw new RuntimeException("No xml file found!");
        }
        switch (type) {
            case DOM:
                useDOMParser(targetFile);
                break;
            case SAX:
                useSAXParser(targetFile);
                break;
            case JAXB:
                useJAXBParser(targetFile);
                break;
        }
    }

    private void useJAXBParser(File file) {
        try {
            JAXBContext context = JAXBContext.newInstance(ShipOrder.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();

            ShipOrder shipOrder = (ShipOrder) unmarshaller.unmarshal(file);
            List<ShipItem> shipItems = shipOrder.getItems();
            for (ShipItem shipItem : shipItems) {
                System.out.println("Title: " + shipItem.getTitle());
                System.out.println("Price: " + shipItem.getPrice());
            }
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    private void useSAXParser(File file) {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            DefaultHandler handler = new ShipOrderHandler();
            saxParser.parse(file, handler);
        } catch (ParserConfigurationException | SAXException | IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private void useDOMParser(File file) {
        try {
            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = builderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(file);
            document.getDocumentElement().normalize();
            NodeList nodeList = document.getElementsByTagName("item");

            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    System.out.println("Title: " + element.getElementsByTagName("title")
                    .item(0).getTextContent());
                    System.out.println("Price: " + element.getElementsByTagName("price")
                            .item(0).getTextContent());
                }
            }
        } catch (ParserConfigurationException | IOException | SAXException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public static XMLService getInstance() {
        if (instance == null) {
            instance = new XMLServiceImpl();
        }
        return instance;
    }
}
