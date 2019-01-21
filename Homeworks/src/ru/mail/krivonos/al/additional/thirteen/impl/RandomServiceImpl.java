package ru.mail.krivonos.al.additional.thirteen.impl;

import ru.mail.krivonos.al.additional.thirteen.RandomService;

import java.util.Random;

public class RandomServiceImpl implements RandomService {

    private Random random;

    public RandomServiceImpl() {
        random = new Random();
    }

    @Override
    public int[] getRandomYearsArray(int amount, int from, int to) {
        int[] years = new int[amount];
        for (int i = 0; i < years.length; i++) {
            years[i] = random.nextInt(to - from + 1) + from;
        }
        return years;
    }
}
