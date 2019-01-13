package ru.mail.krivonos.al.lesson.eleven.impl;

import ru.mail.krivonos.al.lesson.eleven.ListGenerator;
import ru.mail.krivonos.al.lesson.eleven.model.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ListGeneratorImpl implements ListGenerator {

    private Random random = new Random();

    @Override
    public List<Integer> generateIntegersList(int quantity, int minValue, int maxValue) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < quantity; i++) {
            list.add(random.nextInt(maxValue - minValue + 1) + minValue);
        }
        return list;
    }

    @Override
    public List<Person> generatePersonList(int quantity, int minBirthYear, int maxBirthYear) {
        List<Person> people = new ArrayList<>();
        int birthYear;
        //Test counter only need for me to differ created entities
        int testCounter = 0;
        for (int i = 0; i < quantity; i++) {
            birthYear = random.nextInt(maxBirthYear - minBirthYear + 1) + minBirthYear;
            people.add(new Person("TestName" + testCounter, "TestSurname" + testCounter++, birthYear));
        }
        return people;
    }
}
