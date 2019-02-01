package ru.mail.krivonos.al.lesson.nineteen.impl;

import ru.mail.krivonos.al.lesson.nineteen.RandomService;

import java.util.List;
import java.util.Random;

public class RandomServiceImpl implements RandomService {

    private Random random = new Random();
    private static RandomService instance;

    private RandomServiceImpl() {
    }

    @Override
    public void fillList(List<Integer> list, int quantity, int rangeStart, int rangeEnd) {
        for (int i = 0; i < quantity; i++) {
            list.add(random.nextInt(rangeEnd - rangeStart + 1) + rangeStart);
        }
    }

    @Override
    public int getYear(int rangeStart, int rangeEnd) {
        return random.nextInt(rangeEnd - rangeStart + 1) + rangeStart;
    }

    public static RandomService getInstance() {
        if (instance == null) {
            instance = new RandomServiceImpl();
        }
        return instance;
    }
}
