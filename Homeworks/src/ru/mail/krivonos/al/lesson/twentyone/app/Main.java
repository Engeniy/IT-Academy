package ru.mail.krivonos.al.lesson.twentyone.app;

import org.xml.sax.helpers.DefaultHandler;
import ru.mail.krivonos.al.lesson.twentyone.DOMService;
import ru.mail.krivonos.al.lesson.twentyone.SAXService;
import ru.mail.krivonos.al.lesson.twentyone.StAXService;
import ru.mail.krivonos.al.lesson.twentyone.impl.CatalogHandler;
import ru.mail.krivonos.al.lesson.twentyone.impl.DOMServiceImpl;
import ru.mail.krivonos.al.lesson.twentyone.impl.SAXServiceImpl;
import ru.mail.krivonos.al.lesson.twentyone.impl.StAXServiceImpl;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        String fileName = "src/ru/mail/krivonos/al/lesson/twentyone/resources/myxml.xml";
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
    }
}
