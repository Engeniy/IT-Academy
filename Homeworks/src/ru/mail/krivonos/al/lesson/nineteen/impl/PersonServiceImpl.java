package ru.mail.krivonos.al.lesson.nineteen.impl;

import ru.mail.krivonos.al.lesson.nineteen.PersonService;
import ru.mail.krivonos.al.lesson.nineteen.RandomService;
import ru.mail.krivonos.al.lesson.nineteen.model.Person;

import java.util.ArrayList;
import java.util.List;

public class PersonServiceImpl implements PersonService {

    private static PersonService instance;
    private static RandomService randomService = RandomServiceImpl.getInstance();

    private PersonServiceImpl() {
    }

    @Override
    public List<Person> getList(int size) {
        List<Person> people = new ArrayList<>(size);
        //Test values
        String testFirstName = "Tester";
        String testLastName = "Testerovich";
        int testYearFrom = 1989;
        int testYearTo = 2001;
        for (int i = 0; i < size; i++) {
            Person person = Person.newBuilder()
                    .yearOfBirth(randomService.getYear(testYearFrom, testYearTo))
                    .firstName(testFirstName + i)
                    .lastName(testLastName + i)
                    .build();
            people.add(person);
        }
        return people;
    }

    @Override
    public void sortByAge(List<Person> people) {
        Person bufferPerson;
        for (int i = people.size() - 1; i >= 1; i--) {
            for (int j = 0; j < i; j++) {
                if (people.get(j).getYearOfBirth() > people.get(j + 1).getYearOfBirth()) {
                    bufferPerson = people.get(j);
                    people.set(j, people.get(j + 1));
                    people.set(j + 1, bufferPerson);
                }
            }
        }
    }

    @Override
    public List<Person> getSortedList(int size) {
        List<Person> people = getList(size);
        sortByAge(people);
        return people;
    }

    public static PersonService getInstance() {
        if (instance == null) {
            instance = new PersonServiceImpl();
        }
        return instance;
    }
}
