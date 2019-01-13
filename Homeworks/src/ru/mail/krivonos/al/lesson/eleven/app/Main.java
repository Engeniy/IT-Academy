package ru.mail.krivonos.al.lesson.eleven.app;

import ru.mail.krivonos.al.lesson.eleven.CollectionService;
import ru.mail.krivonos.al.lesson.eleven.ConsoleService;
import ru.mail.krivonos.al.lesson.eleven.ListGenerator;
import ru.mail.krivonos.al.lesson.eleven.impl.CollectionServiceImpl;
import ru.mail.krivonos.al.lesson.eleven.impl.ConsoleServiceImpl;
import ru.mail.krivonos.al.lesson.eleven.impl.ListGeneratorImpl;
import ru.mail.krivonos.al.lesson.eleven.model.Person;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ConsoleService consoleService = new ConsoleServiceImpl(scanner);
        ListGenerator listGenerator = new ListGeneratorImpl();
        CollectionService collectionService = new CollectionServiceImpl();

        System.out.println("You are creating collection with random integers. Please input quantity:");
        int quantity = consoleService.inputNumber();
        System.out.println("Please input integers min value:");
        int minValue = consoleService.inputNumber();
        System.out.println("Please input integers max value:");
        int maxValue = consoleService.inputNumber();
        List<Integer> integers = listGenerator.generateIntegersList(quantity, minValue, maxValue);
        collectionService.printNumberEntriesQuantity(integers);
        System.out.println("----------------------------------");

        Set<String> strings = new HashSet<>();
        System.out.println("You are filling collection from console. Please input number of lines you want to use:");
        int linesNumber = consoleService.inputNumber();
        consoleService.fillCollectionWithStrings(strings, linesNumber);
        System.out.println("Please input first conditional string:");
        String firstCondition = consoleService.inputLine();
        System.out.println("Please input second conditional string:");
        String secondCondition = consoleService.inputLine();
        System.out.println("Your collection before changing:");
        collectionService.printCollection(strings);
        System.out.println();
        collectionService.deleteElementsWithConditionalSubstrings(strings, firstCondition, secondCondition);
        System.out.println("Your collection after changing:");
        collectionService.printCollection(strings);
        System.out.println("----------------------------------");

        System.out.println("You are creating collection with random people. Please input number of people you want to create:");
        int peopleNumber = consoleService.inputNumber();
        System.out.println("Please input min birth year:");
        int minBirthYear = consoleService.inputNumber();
        System.out.println("Please input max birth year:");
        int maxBirthYear = consoleService.inputNumber();
        List<Person> people = listGenerator.generatePersonList(peopleNumber, minBirthYear, maxBirthYear);
        collectionService.printPersonByAge(people);
    }
}
