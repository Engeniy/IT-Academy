package ru.mail.krivonos.al.test.impl;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import ru.mail.krivonos.al.test.XMLParserService;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class DOMParserServiceImpl implements XMLParserService {

    private static XMLParserService instance;

    private DOMParserServiceImpl() {
    }

    @Override
    public String parse(File file) {
        Double sum = 0.0;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{");

        try {
            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = builderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(file);
            document.getDocumentElement().normalize();
            NodeList nodeList = document.getElementsByTagName("Item");

            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    String productName = element.getElementsByTagName("ProductName")
                            .item(0).getTextContent();
                    stringBuilder.append(productName);
                    stringBuilder.append(" - ");
                    String price = element.getElementsByTagName("USPrice")
                            .item(0).getTextContent();
                    sum += Double.valueOf(price);
                    stringBuilder.append(price);
                    stringBuilder.append(", ");
                }
            }
            stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length());
            stringBuilder.append(":");
            stringBuilder.append(sum);
            stringBuilder.append("}");
        } catch (ParserConfigurationException | IOException | SAXException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return stringBuilder.toString();
    }

    public static XMLParserService getInstance() {
        if (instance == null) {
            instance = new DOMParserServiceImpl();
        }
        return instance;
    }
}
