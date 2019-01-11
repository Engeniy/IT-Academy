package ru.mail.krivonos.al.lesson.ten;

import ru.mail.krivonos.al.lesson.ten.model.Person;

import java.util.Collection;

public interface CollectionService {

    void fillWithRandomIntegersFromRange(Collection<Integer> collection, int quantity, int rangeStart, int rangeEnd);

    void printTwoMaxNumbers(Collection<Integer> collection);

    void replaceSubstring(Collection<String> collection, String replaceable, String replacement);

    void printAdultOrInfant(Collection<? extends Person> collection);

    void printCollection(Collection collection);

    void printCollectionUsingToString(Collection collection);
}

