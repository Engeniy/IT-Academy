package ru.mail.krivonos.al.lesson.twentyone;

import ru.mail.krivonos.al.lesson.twentyone.model.Catalog;

import java.io.File;

public interface JAXBService {

    Catalog unmarshall(File file, Class<?> clazz);
}
