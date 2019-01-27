package ru.mail.krivonos.al.lesson.seventeen.task3.impl;

import ru.mail.krivonos.al.lesson.seventeen.task3.RandomService;

import java.util.Random;

public class RandomServiceImpl implements RandomService {

    private Random random = new Random();

    @Override
    public String getLine(int size, int range) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < size - 1; i++) {
            stringBuilder.append(String.valueOf(random.nextInt(2 * range + 1) - range));
            stringBuilder.append(",");
        }
        stringBuilder.append(String.valueOf(random.nextInt(2 * range + 1) - range));
        return stringBuilder.toString();
    }
}
