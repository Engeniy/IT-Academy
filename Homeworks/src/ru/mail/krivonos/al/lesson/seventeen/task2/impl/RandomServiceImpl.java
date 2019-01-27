package ru.mail.krivonos.al.lesson.seventeen.task2.impl;

import ru.mail.krivonos.al.lesson.seventeen.task2.RandomService;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomServiceImpl implements RandomService {

    private Random random = new Random();

    @Override
    public List<Integer> getIntegers(int size, int range) {
        List<Integer> integers = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            integers.add(random.nextInt(2 * range + 1) - range);
        }
        return integers;
    }
}
