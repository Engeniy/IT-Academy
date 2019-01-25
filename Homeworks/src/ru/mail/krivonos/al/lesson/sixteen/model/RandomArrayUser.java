package ru.mail.krivonos.al.lesson.sixteen.model;

import ru.mail.krivonos.al.lesson.sixteen.RandomService;

public abstract class RandomArrayUser extends Thread {

    protected int capacity;
    protected int range;
    protected final RandomService randomService;

    public RandomArrayUser(String s, int capacity, int range, RandomService randomService) {
        super(s);
        this.capacity = capacity;
        this.range = range;
        this.randomService = randomService;
    }
}
