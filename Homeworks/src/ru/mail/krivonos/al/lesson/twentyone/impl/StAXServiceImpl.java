package ru.mail.krivonos.al.lesson.twentyone.impl;

import ru.mail.krivonos.al.lesson.twentyone.StAXService;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class StAXServiceImpl implements StAXService {

    private static StAXService instance;

    @Override
    public void parseFile(File file) {
        boolean authorEvent = false;
        boolean titleEvent = false;
        boolean genreEvent = false;
        boolean publishDateEvent = false;
        boolean descriptionEvent = false;
        boolean priceEvent = false;
        double sum = 0;
        int counter = 0;
        int bookCounter = 0;

        XMLInputFactory factory = XMLInputFactory.newInstance();
        try {
            XMLEventReader eventReader = factory.createXMLEventReader(new FileReader(file));

            while (eventReader.hasNext()) {
                XMLEvent event = eventReader.nextEvent();

                switch (event.getEventType()) {
                    case XMLEvent.START_ELEMENT:
                        StartElement startElement = event.asStartElement();
                        String name = startElement.getName().getLocalPart();

                        if (name.equalsIgnoreCase("book")) {
                            bookCounter++;
                        }
                        if (name.equalsIgnoreCase("author")) {
                            authorEvent = true;
                        }
                        if (name.equalsIgnoreCase("title")) {
                            titleEvent = true;
                        }
                        if (name.equalsIgnoreCase("genre")) {
                            genreEvent = true;
                        }
                        if (name.equalsIgnoreCase("publish_date")) {
                            publishDateEvent = true;
                        }
                        if (name.equalsIgnoreCase("description")) {
                            descriptionEvent = true;
                        }
                        if (name.equalsIgnoreCase("price")) {
                            priceEvent = true;
                        }
                        break;

                    case XMLEvent.CHARACTERS:
                        Characters characters = event.asCharacters();

                        if (authorEvent) {
                            System.out.println("Author: " + characters.getData());
                            authorEvent = false;
                        }
                        if (titleEvent) {
                            System.out.println("Title: " + characters.getData());
                            titleEvent = false;
                        }
                        if (genreEvent) {
                            System.out.println("Genre: " + characters.getData());
                            genreEvent = false;
                        }
                        if (publishDateEvent) {
                            System.out.println("Publish date: " + characters.getData());
                            publishDateEvent = false;
                        }
                        if (descriptionEvent) {
                            System.out.println("Description: " + characters.getData());
                            descriptionEvent = false;
                        }
                        if (priceEvent) {
                            double price = Double.valueOf(characters.getData());
                            System.out.println("Price: " + price);
                            sum += price;
                            counter++;
                            priceEvent = false;
                        }
                        break;

                    case XMLEvent.END_DOCUMENT:
                        System.out.println("Books amount: " + bookCounter);
                        System.out.println("Average: " + (sum / counter));
                        break;
                }
            }
        } catch (FileNotFoundException | XMLStreamException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    private StAXServiceImpl() {
    }

    public static StAXService getInstance() {
        if (instance == null) {
            instance = new StAXServiceImpl();
        }
        return instance;
    }
}
