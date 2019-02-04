package ru.mail.krivonos.al.lesson.twentyone.impl;

import ru.mail.krivonos.al.lesson.twentyone.JAXBService;
import ru.mail.krivonos.al.lesson.twentyone.model.Catalog;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class JAXBServiceImpl implements JAXBService {

    private static JAXBService instance;

    @Override
    public Catalog unmarshall(File file, Class<?> clazz) {
        try {
            JAXBContext context = JAXBContext.newInstance(clazz);
            Unmarshaller unmarshaller = context.createUnmarshaller();

            Catalog catalog = (Catalog) unmarshaller.unmarshal(file);
            return catalog;
        } catch (JAXBException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    private JAXBServiceImpl() {
    }

    public static JAXBService getInstance() {
        if (instance == null) {
            instance = new JAXBServiceImpl();
        }
        return instance;
    }
}
