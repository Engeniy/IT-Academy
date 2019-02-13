package ru.mail.krivonos.al.additional.twentyone.multithreading.impl;

import ru.mail.krivonos.al.additional.twentyone.multithreading.RandomService;

import java.util.Random;

public class RandomServiceImpl implements RandomService {

    private static RandomService instance;
    private Random random = new Random();

    @Override
    public int getNumber(int rangeStart, int rangeEnd) {
        return random.nextInt(rangeEnd - rangeStart + 1) + rangeStart;
    }

    @Override
    public int getNumber(int range) {
        return getNumber(-range, range);
    }

    private RandomServiceImpl() {
    }

    public static RandomService getInstance() {
        if (instance == null) {
            instance = new RandomServiceImpl();
        }
        return instance;
    }
}
