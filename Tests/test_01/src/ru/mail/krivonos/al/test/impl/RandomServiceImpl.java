package ru.mail.krivonos.al.test.impl;

import ru.mail.krivonos.al.test.RandomService;

import java.util.Random;

public class RandomServiceImpl implements RandomService {

    private Random random;

    public RandomServiceImpl() {
        random = new Random();
    }

    // TODO add validation
    @Override
    public int[] getRandomIntArray(int quantity, int from) {
        int[] array = new int[quantity];
        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt(2 * from + 1) - from;
        }
        return array;
    }
}
