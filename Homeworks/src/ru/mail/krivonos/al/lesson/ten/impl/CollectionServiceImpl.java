package ru.mail.krivonos.al.lesson.ten.impl;

import ru.mail.krivonos.al.lesson.ten.CollectionService;
import ru.mail.krivonos.al.lesson.ten.model.Person;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

public class CollectionServiceImpl implements CollectionService {

    private Random random = new Random();

    @Override
    public void fillWithRandomIntegersFromRange(Collection<Integer> collection, int quantity, int rangeStart, int rangeEnd) {
        for (int i = 0; i < quantity; i++) {
            collection.add(random.nextInt(rangeEnd + 1 - rangeStart) + rangeStart);
        }
    }

    //In this method primitive types used for better method performance and because we actually know what type we will use.
    @Override
    public void printTwoMaxNumbers(Collection<Integer> collection) {
        int firstMax = Integer.MIN_VALUE;
        int secondMax = Integer.MIN_VALUE;
        int buffer;

        for (Integer element : collection) {
            buffer = Math.min(element, firstMax);
            firstMax = Math.max(element, firstMax);
            secondMax = Math.max(secondMax, buffer);

        }
        System.out.println("In your collection two max values are " + firstMax + " and " + secondMax + ".");
    }

    @Override
    public void replaceSubstring(Collection<String> collection, String replaceable, String replacement) {
        Collection<String> buffer = new ArrayList<>();

        for (String element : collection) {
            element = element.replace(replaceable, replacement);
            buffer.add(element);
        }
        collection.clear();
        collection.addAll(buffer);
    }

    @Override
    public void printAdultOrInfant(Collection<? extends Person> collection) {
        for (Person person : collection) {
            if (person.getAge() >= 18) {
                System.out.println("Adult: " + person.getName() + " " + person.getSurname() + " age: " + person.getAge());
            } else {
                System.out.println("Infant: " + person.getName() + " " + person.getSurname() + " age: " + person.getAge());
            }
        }
    }

    @Override
    public void printCollection(Collection collection) {
        System.out.println("Your collection:");
        for (Object element : collection) {
            System.out.print(element + " ");
        }
        System.out.println();
    }

    @Override
    public void printCollectionUsingToString(Collection collection) {
        System.out.println("Your collection:\n" + collection);
    }
}
