package ru.mail.krivonos.al.lesson.twentyone.impl;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import ru.mail.krivonos.al.lesson.twentyone.DOMService;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class DOMServiceImpl implements DOMService {

    private static DOMService instance;

    @Override
    public void parseFile(File file) {
        double sum = 0;
        int counter = 0;
        int bookCounter = 0;
        double price;

        try {
            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = builderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(file);
            document.getDocumentElement().normalize();
            NodeList nodeList = document.getElementsByTagName("book");

            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    bookCounter++;
                    price = Double.valueOf(element.getElementsByTagName("price").item(0).getTextContent());
                    sum += price;
                    counter++;
                    System.out.println("Author: " + element.getElementsByTagName("author")
                            .item(0).getTextContent());
                    System.out.println("Title: " + element.getElementsByTagName("title")
                            .item(0).getTextContent());
                    System.out.println("Genre: " + element.getElementsByTagName("genre")
                            .item(0).getTextContent());
                    System.out.println("Price: " + price);
                    System.out.println("Publish date: " + element.getElementsByTagName("publish_date")
                            .item(0).getTextContent());
                    System.out.println("Description: " + element.getElementsByTagName("description")
                            .item(0).getTextContent());
                }
            }
            System.out.println("Books amount: " + bookCounter);
            System.out.println("Average: " + (sum / counter));
        } catch (ParserConfigurationException | SAXException | IOException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    private DOMServiceImpl() {
    }

    public static DOMService getInstance() {
        if (instance == null) {
            instance = new DOMServiceImpl();
        }
        return instance;
    }
}
