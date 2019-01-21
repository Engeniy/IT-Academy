package ru.mail.krivonos.al.additional.thirteen.app;

import ru.mail.krivonos.al.additional.thirteen.FileService;
import ru.mail.krivonos.al.additional.thirteen.PersonService;
import ru.mail.krivonos.al.additional.thirteen.impl.PersonServiceImpl;
import ru.mail.krivonos.al.additional.thirteen.RandomService;
import ru.mail.krivonos.al.additional.thirteen.impl.FileServiceImpl;
import ru.mail.krivonos.al.additional.thirteen.impl.RandomServiceImpl;
import ru.mail.krivonos.al.additional.thirteen.model.Person;

import java.io.File;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        RandomService randomService = new RandomServiceImpl();
        int[] years = randomService.getRandomYearsArray(10, 1960, 1970);

        File targetFile = new File("/home/alex/Documents/IT-Academy/Homeworks/src/ru/mail/krivonos/al/additional/thirteen/Persons.txt");
        FileService fileService = new FileServiceImpl();
        List<String> content = fileService.readFile(targetFile);
        fileService.writeWithYears(targetFile, content, years);
        content = fileService.readFile(targetFile);
        PersonService personService = new PersonServiceImpl();
        List<Person> people = personService.parseToPersons(content);
        personService.printYoungest(people);
    }
}
