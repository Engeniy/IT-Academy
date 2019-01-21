package ru.mail.krivonos.al.additional.thirteen.model;

public class Person {

    private int yearOfBirth;
    private String name;
    private String surname;

    public Person(int yearOfBirth, String name, String surname) {
        this.yearOfBirth = yearOfBirth;
        this.name = name;
        this.surname = surname;
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    @Override
    public String toString() {
        return "Person{" +
                "yearOfBirth=" + yearOfBirth +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                '}';
    }
}
