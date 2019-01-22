package ru.mail.krivonos.al.test.model;

public class Student {
    private int number;
    private String name;
    private int course;
    private int group;

    public Student(int number, String name, int course, int group) {
        this.number = number;
        this.name = name;
        this.course = course;
        this.group = group;
    }

    @Override
    public String toString() {
        return "Student{" +
                "number=" + number +
                ", name='" + name + '\'' +
                ", course=" + course +
                ", group=" + group +
                '}';
    }
}
