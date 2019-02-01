package ru.mail.krivonos.al.lesson.nineteen.impl;

import ru.mail.krivonos.al.lesson.nineteen.OutputService;
import ru.mail.krivonos.al.lesson.nineteen.model.Person;

import java.util.Collection;

public class ConsoleOutputService implements OutputService {

    private static OutputService instance;

    private ConsoleOutputService() {
    }

    @Override
    public void outputCollection(Collection collection, String message) {
        System.out.println(message);
        StringBuilder stringBuilder = new StringBuilder();
        for (Object element : collection) {
            stringBuilder.append(element);
            stringBuilder.append(" ");
        }
        System.out.println(stringBuilder.toString().trim());
    }

    @Override
    public void outputPersonCollection(Collection<Person> people, String message) {
        System.out.println(message);
        StringBuilder stringBuilder = new StringBuilder();
        for (Person person : people) {
            stringBuilder.append(person);
            stringBuilder.append(System.lineSeparator());
        }
        System.out.println(stringBuilder.toString().trim());
    }


    public static OutputService getInstance() {
        if (instance == null) {
            instance = new ConsoleOutputService();
        }
        return instance;
    }
}
