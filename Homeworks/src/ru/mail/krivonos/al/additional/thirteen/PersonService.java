package ru.mail.krivonos.al.additional.thirteen;

import ru.mail.krivonos.al.additional.thirteen.model.Person;

import java.util.List;

public interface PersonService {

    List<Person> parseToPersons(List<String> content);

    void printYoungest(List<Person> people);
}
