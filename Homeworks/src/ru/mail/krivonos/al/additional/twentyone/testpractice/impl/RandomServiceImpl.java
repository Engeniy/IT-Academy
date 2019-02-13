package ru.mail.krivonos.al.additional.twentyone.testpractice.impl;

import ru.mail.krivonos.al.additional.twentyone.testpractice.RandomService;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomServiceImpl implements RandomService {

    private static RandomService instance;
    private Random random = new Random();

    private RandomServiceImpl() {
    }

    @Override
    public int[] getArray(int size, int range) {
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt(2 * range + 1) - range;
        }
        return array;
    }

    @Override
    public List<Integer> getList(int size, int range) {
        List<Integer> list = new ArrayList<>(size);
        int[] array = getArray(size, range);
        for (int element : array) {
            list.add(element);
        }
        return list;
    }

    @Override
    public int getNumber(int range) {
        return random.nextInt(range);
    }

    public static RandomService getInstance() {
        if (instance == null) {
            synchronized (RandomServiceImpl.class) {
                if (instance == null) {
                    instance = new RandomServiceImpl();
                }
            }
        }
        return instance;
    }
}
