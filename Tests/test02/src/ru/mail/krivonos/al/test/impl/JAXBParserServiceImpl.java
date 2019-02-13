package ru.mail.krivonos.al.test.impl;

import ru.mail.krivonos.al.test.XMLParserService;
import ru.mail.krivonos.al.test.model.Item;
import ru.mail.krivonos.al.test.model.PurchaseOrder;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.math.BigDecimal;
import java.util.List;

public class JAXBParserServiceImpl implements XMLParserService {

    private static XMLParserService instance;

    private JAXBParserServiceImpl() {
    }

    @Override
    public String parse(File file) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{");
        try {
            JAXBContext context = JAXBContext.newInstance(PurchaseOrder.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();

            PurchaseOrder purchaseOrder = (PurchaseOrder) unmarshaller.unmarshal(file);
            List<Item> items = purchaseOrder.getItems();
            BigDecimal sum = BigDecimal.valueOf(0);
            for (Item item : items) {
                String productName = item.getProductName();
                stringBuilder.append(productName);
                stringBuilder.append(" - ");
                BigDecimal usPrice = item.getUsPrice();
                stringBuilder.append(usPrice);
                stringBuilder.append(", ");
                sum = sum.add(usPrice);
            }
            stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length());
            stringBuilder.append(":");
            stringBuilder.append(sum);
            stringBuilder.append("}");
        } catch (JAXBException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return stringBuilder.toString();
    }

    public static XMLParserService getInstance() {
        if (instance == null) {
            instance = new JAXBParserServiceImpl();
        }
        return instance;
    }
}
