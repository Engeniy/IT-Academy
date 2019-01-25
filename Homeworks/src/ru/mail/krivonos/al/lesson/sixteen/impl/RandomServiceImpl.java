package ru.mail.krivonos.al.lesson.sixteen.impl;

import ru.mail.krivonos.al.lesson.sixteen.RandomService;

import java.util.Random;

public class RandomServiceImpl implements RandomService {

    private Random random = new Random();

    @Override
    public int[] getIntegersArray(int capacity, int range) {
        int[] array = new int[capacity];
        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt(2 * range + 1) - range;
        }
        return array;
    }
}
