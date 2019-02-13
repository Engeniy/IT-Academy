package ru.mail.krivonos.al.test.impl;

import ru.mail.krivonos.al.test.XMLParserService;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class StAXParserServiceImpl implements XMLParserService {

    private static XMLParserService instance;

    private StAXParserServiceImpl() {
    }

    @Override
    public String parse(File file) {
        boolean productNameEvent = false;
        boolean usPriceEvent = false;
        Double sum = 0.0;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{");

        XMLInputFactory factory = XMLInputFactory.newInstance();
        try {
            XMLEventReader eventReader = factory.createXMLEventReader(new FileReader(file));

            while (eventReader.hasNext()) {
                XMLEvent event = eventReader.nextEvent();

                switch (event.getEventType()) {
                    case XMLEvent.START_ELEMENT:
                        StartElement startElement = event.asStartElement();
                        String name = startElement.getName().getLocalPart();

                        if (name.equals("ProductName")) {
                            productNameEvent = true;
                        }
                        if (name.equals("USPrice")) {
                            usPriceEvent = true;
                        }
                        break;
                    case XMLEvent.CHARACTERS:
                        Characters characters = event.asCharacters();

                        if (productNameEvent) {
                            String productName = characters.getData();
                            stringBuilder.append(productName);
                            stringBuilder.append(" - ");
                            productNameEvent = false;
                        }
                        if (usPriceEvent) {
                            String usPrice = characters.getData();
                            sum += Double.valueOf(usPrice);
                            stringBuilder.append(usPrice);
                            stringBuilder.append(", ");
                            usPriceEvent = false;
                        }
                        break;
                }
            }
        } catch (XMLStreamException | FileNotFoundException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length());
        stringBuilder.append(":");
        stringBuilder.append(sum);
        stringBuilder.append("}");
        return stringBuilder.toString();
    }

    public static XMLParserService getInstance() {
        if (instance == null) {
            instance = new StAXParserServiceImpl();
        }
        return instance;
    }
}
