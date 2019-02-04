package ru.mail.krivonos.al.lesson.twentyone.app;

import org.xml.sax.helpers.DefaultHandler;
import ru.mail.krivonos.al.lesson.twentyone.DOMService;
import ru.mail.krivonos.al.lesson.twentyone.JAXBService;
import ru.mail.krivonos.al.lesson.twentyone.SAXService;
import ru.mail.krivonos.al.lesson.twentyone.StAXService;
import ru.mail.krivonos.al.lesson.twentyone.impl.*;
import ru.mail.krivonos.al.lesson.twentyone.model.Catalog;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        String fs = File.separator;
        String fileName = "src" + fs + "ru" + fs + "mail" + fs + "krivonos" + fs + "al" + fs + "lesson" + fs +
                "twentyone" + fs + "resources" + fs + "myxml.xml";
        File file = new File(fileName);
        SAXService saxService = SAXServiceImpl.getInstance();
        DefaultHandler handler = new CatalogHandler();
        saxService.parseFile(file, handler);
        System.out.println("-------------------------------------");
        System.out.println();

        StAXService stAXService = StAXServiceImpl.getInstance();
        stAXService.parseFile(file);
        System.out.println("-------------------------------------");
        System.out.println();

        DOMService domService = DOMServiceImpl.getInstance();
        domService.parseFile(file);
        System.out.println("-------------------------------------");
        System.out.println();

        JAXBService jaxbService = JAXBServiceImpl.getInstance();
        Catalog catalog = jaxbService.unmarshall(file, Catalog.class);
        System.out.println(catalog);
    }
}
