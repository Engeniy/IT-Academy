package ru.mail.krivonos.al.test.handler;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class PurchaseOrderHandler extends DefaultHandler {

    private boolean productNameEvent;
    private boolean priceEvent;
    private StringBuilder stringBuilder = new StringBuilder();
    private Double sum = 0.0;

    @Override
    public void startDocument() throws SAXException {
        stringBuilder.append("{");
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equals("ProductName")) {
            productNameEvent = true;
        }
        if (qName.equals("USPrice")) {
            priceEvent = true;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if (productNameEvent) {
            String name = new String(ch, start, length);
            stringBuilder.append(name);
            stringBuilder.append(" - ");
            productNameEvent = false;
        }
        if (priceEvent) {
            String price = new String(ch, start, length);
            stringBuilder.append(price);
            stringBuilder.append(", ");
            sum += Double.valueOf(price);
            priceEvent = false;
        }
    }

    @Override
    public void endDocument() throws SAXException {
        stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length());
        stringBuilder.append(":");
        stringBuilder.append(sum);
        stringBuilder.append("}");
    }

    public String getResult() {
        return stringBuilder.toString();
    }
}
