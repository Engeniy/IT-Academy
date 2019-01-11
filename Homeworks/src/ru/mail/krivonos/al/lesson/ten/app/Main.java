package ru.mail.krivonos.al.lesson.ten.app;

import ru.mail.krivonos.al.lesson.ten.CollectionService;
import ru.mail.krivonos.al.lesson.ten.impl.CollectionServiceImpl;
import ru.mail.krivonos.al.lesson.ten.ConsoleService;
import ru.mail.krivonos.al.lesson.ten.impl.ConsoleServiceImpl;
import ru.mail.krivonos.al.lesson.ten.model.Person;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ConsoleService consoleService = new ConsoleServiceImpl(scanner);
        CollectionService collectionService = new CollectionServiceImpl();

        Collection<Integer> integers = new ArrayList<>();
        System.out.println("You are filling collection with random integers. Please input quantity:");
        int quantity = consoleService.inputNumber();
        System.out.println("Please input integers range start:");
        int rangeStart = consoleService.inputNumber();
        System.out.println("Please input integers range end:");
        int rangeEnd = consoleService.inputNumber();
        collectionService.fillWithRandomIntegersFromRange(integers, quantity, rangeStart, rangeEnd);
        collectionService.printTwoMaxNumbers(integers);
        System.out.println();

        Collection<String> strings = new ArrayList<>();
        System.out.println("You are filling collection from console. Please input number of lines you want to use:");
        int linesNumber = consoleService.inputNumber();
        consoleService.fillCollectionWithStrings(strings, linesNumber);
        System.out.println("Please input string you want to replace in target collection:");
        String replaceable = consoleService.inputLine();
        System.out.println("Please input replacement string:");
        String replacement = consoleService.inputLine();
        collectionService.replaceSubstring(strings, replaceable, replacement);
        collectionService.printCollection(strings);
        //Another variant, with more laconic realisation as for me
        collectionService.printCollectionUsingToString(strings);
        System.out.println();

        Collection<Person> people = new ArrayList<>();
        System.out.println("You are filling collection with people. Please input number of people you want to create:");
        int peopleNumber = consoleService.inputNumber();
        String personName;
        String personSurname;
        int personAge;
        for (int i = 0; i < peopleNumber; i++) {
            System.out.println("You are creating new person (" + (peopleNumber - i) + " left). Please input person name:");
            personName = consoleService.inputLine();
            System.out.println("Please input person's surname:");
            personSurname = consoleService.inputLine();
            System.out.println("Please input person's age :");
            personAge = consoleService.inputNumber();
            people.add(new Person(personName, personSurname, personAge));
        }
        collectionService.printAdultOrInfant(people);
    }
}
