package ru.mail.krivonos.al.lesson.nineteen;

import ru.mail.krivonos.al.lesson.nineteen.model.Person;

import java.util.Collection;

public interface OutputService {

    void outputCollection(Collection<? extends Number> collection, String message);

    void outputPersonCollection(Collection<Person> people, String message);
}
