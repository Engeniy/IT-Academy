package ru.mail.krivonos.al.lesson.eleven.impl;

import ru.mail.krivonos.al.lesson.eleven.CollectionService;
import ru.mail.krivonos.al.lesson.eleven.model.Person;

import java.util.*;

public class CollectionServiceImpl implements CollectionService {

    @Override
    public void printNumberEntriesQuantity(Collection<Integer> collection) {
        Set<Integer> integerSet = new HashSet<>(collection);
        int counter;
        for (Integer setElement : integerSet) {
            counter = 0;
            for (Integer collectionElement : collection) {
                if (setElement.equals(collectionElement)) {
                    counter++;
                }
            }
            System.out.println("Your collection contains number \"" + setElement + "\" " + counter + " times.");
        }
    }

    @Override
    public void deleteElementsWithConditionalSubstrings(Collection<String> collection, String firstCondition, String secondCondition) {
        Iterator<String> iterator = collection.iterator();
        String element;
        while (iterator.hasNext()) {
            element = iterator.next();
            if (element.contains(firstCondition)) {
                iterator.remove();
            } else if (element.contains(secondCondition)) {
                iterator.remove();
            }
        }
    }

    @Override
    public void printCollection(Collection collection) {
        for (Object element : collection) {
            System.out.println(element);
        }
    }

    @Override
    public void printPersonByAge(Collection<Person> collection) {
        List<Person> people = new LinkedList<>(collection);
        Person buffer;
        for (int i = people.size() - 1; i >= 1; i--) {
            for (int j = 0; j < i; j++) {
                if (people.get(j).getBirthYear() > people.get(j + 1).getBirthYear()) {
                    buffer = people.get(j);
                    people.set(j, people.get(j + 1));
                    people.set(j + 1, buffer);
                }
            }
        }
        printCollection(people);
    }
}
