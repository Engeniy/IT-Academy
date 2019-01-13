package ru.mail.krivonos.al.lesson.eleven;

import ru.mail.krivonos.al.lesson.eleven.model.Person;

import java.util.Collection;

public interface CollectionService {

    void printNumberEntriesQuantity(Collection<Integer> collection);

    void deleteElementsWithConditionalSubstrings(Collection<String> collection, String firstCondition, String secondCondition);

    void printCollection(Collection collection);

    void printPersonByAge(Collection<Person> collection);
}
