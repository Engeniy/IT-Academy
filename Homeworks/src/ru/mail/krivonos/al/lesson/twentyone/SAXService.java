package ru.mail.krivonos.al.lesson.twentyone;

import org.xml.sax.helpers.DefaultHandler;

import java.io.File;

public interface SAXService {

    void parseFile(File file, DefaultHandler handler);
}
