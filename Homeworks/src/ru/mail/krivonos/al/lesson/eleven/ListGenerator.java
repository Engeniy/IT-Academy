package ru.mail.krivonos.al.lesson.eleven;

import ru.mail.krivonos.al.lesson.eleven.model.Person;

import java.util.List;

public interface ListGenerator {

    List<Integer> generateIntegersList(int quantity, int minValue, int maxValue);

    List<Person> generatePersonList(int quantity, int minBirthYear, int maxBirthYear);
}
