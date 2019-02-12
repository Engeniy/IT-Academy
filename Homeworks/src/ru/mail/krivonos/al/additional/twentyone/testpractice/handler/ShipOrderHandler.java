package ru.mail.krivonos.al.additional.twentyone.testpractice.handler;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class ShipOrderHandler extends DefaultHandler {

    private boolean titleEvent;
    private boolean priceEvent;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equals("title")) {
            titleEvent = true;
        }
        if (qName.equals("price")) {
            priceEvent = true;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if (titleEvent) {
            String title = new String(ch, start, length);
            System.out.println("Title: " + title);
            titleEvent = false;
        }
        if (priceEvent) {
            String price = new String(ch, start, length);
            System.out.println("Price: " + price);
            priceEvent = false;
        }
    }
}
