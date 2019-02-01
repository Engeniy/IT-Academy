package ru.mail.krivonos.al.lesson.nineteen.app;

import ru.mail.krivonos.al.lesson.nineteen.ListFactory;
import ru.mail.krivonos.al.lesson.nineteen.OutputService;
import ru.mail.krivonos.al.lesson.nineteen.PersonService;
import ru.mail.krivonos.al.lesson.nineteen.impl.ConsoleOutputService;
import ru.mail.krivonos.al.lesson.nineteen.impl.ListFactoryImpl;
import ru.mail.krivonos.al.lesson.nineteen.impl.PersonServiceImpl;
import ru.mail.krivonos.al.lesson.nineteen.model.ListType;
import ru.mail.krivonos.al.lesson.nineteen.model.Person;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        ListType type = ListType.ARRAY_LIST;
        int listSize = 50;
        int rangeStart = 0;
        int rangeEnd = 20;

        ListFactory listFactory = ListFactoryImpl.getInstance();
        List<Integer> createdList = listFactory.createFilledList(type, listSize, rangeStart, rangeEnd);
        OutputService outputService = ConsoleOutputService.getInstance();
        outputService.outputCollection(createdList, "Your ArrayList:");

        type = ListType.LINKED_LIST;
        createdList = listFactory.createFilledList(type, listSize, rangeStart, rangeEnd);
        outputService.outputCollection(createdList, "Your LinkedList:");

        int peopleAmount = 10;
        PersonService personService = PersonServiceImpl.getInstance();
        List<Person> people = personService.getSortedList(peopleAmount);
        outputService.outputPersonCollection(people, "Your people info:");
    }
}
