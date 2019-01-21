package ru.mail.krivonos.al.additional.thirteen.impl;

import ru.mail.krivonos.al.additional.thirteen.PersonService;
import ru.mail.krivonos.al.additional.thirteen.model.Person;

import java.util.ArrayList;
import java.util.List;

public class PersonServiceImpl implements PersonService {

    @Override
    public List<Person> parseToPersons(List<String> content) {
        List<Person> people = new ArrayList<>();
        for (String line : content) {
            String[] values = line.split(" ");
            people.add(new Person(Integer.parseInt(values[2]), values[0], values[1]));
        }
        return people;
    }

    @Override
    public void printYoungest(List<Person> people) {
        Person youngest = people.get(0);
        for (Person person : people) {
            if (youngest.getYearOfBirth() < person.getYearOfBirth()) {
                youngest = person;
            }
        }
        System.out.println(youngest);
    }
}
