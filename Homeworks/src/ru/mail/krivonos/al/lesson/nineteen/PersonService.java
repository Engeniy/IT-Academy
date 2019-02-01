package ru.mail.krivonos.al.lesson.nineteen;

import ru.mail.krivonos.al.lesson.nineteen.model.Person;

import java.util.List;

public interface PersonService {

    List<Person> getList(int size);

    List<Person> getSortedList(int size);

    void sortByAge(List<Person> people);
}
