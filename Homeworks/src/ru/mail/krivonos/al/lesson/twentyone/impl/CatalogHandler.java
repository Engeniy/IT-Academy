package ru.mail.krivonos.al.lesson.twentyone.impl;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class CatalogHandler extends DefaultHandler {
    private boolean authorEvent = false;
    private boolean titleEvent = false;
    private boolean genreEvent = false;
    private boolean publishDateEvent = false;
    private boolean descriptionEvent = false;
    private boolean priceEvent = false;
    private double sum = 0;
    private int counter = 0;
    private int bookCounter = 0;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equalsIgnoreCase("book")) {
            bookCounter++;
        }
        if (qName.equalsIgnoreCase("author")) {
            authorEvent = true;
        }
        if (qName.equalsIgnoreCase("title")) {
            titleEvent = true;
        }
        if (qName.equalsIgnoreCase("genre")) {
            genreEvent = true;
        }
        if (qName.equalsIgnoreCase("publish_date")) {
            publishDateEvent = true;
        }
        if (qName.equalsIgnoreCase("description")) {
            descriptionEvent = true;
        }
        if (qName.equalsIgnoreCase("price")) {
            priceEvent = true;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if (authorEvent) {
            String author = new String(ch, start, length);
            System.out.println("Author: " + author);
            authorEvent = false;
        }
        if (titleEvent) {
            String title = new String(ch, start, length);
            System.out.println("Title: " + title);
            titleEvent = false;
        }
        if (genreEvent) {
            String genre = new String(ch, start, length);
            System.out.println("Genre: " + genre);
            genreEvent = false;
        }
        if (publishDateEvent) {
            String publishDate = new String(ch, start, length);
            System.out.println("Publish date: " + publishDate);
            publishDateEvent = false;
        }
        if (descriptionEvent) {
            String description = new String(ch, start, length);
            System.out.println("Description: " + description);
            descriptionEvent = false;
        }
        if (priceEvent) {
            String priceStr = new String(ch, start, length);
            double price = Double.valueOf(priceStr);
            System.out.println("Price: " + price);
            sum += price;
            counter++;
            priceEvent = false;
        }
    }

    @Override
    public void endDocument() throws SAXException {
        System.out.println("Books amount: " + bookCounter);
        System.out.println("Average: " + (sum / counter));
    }
}
