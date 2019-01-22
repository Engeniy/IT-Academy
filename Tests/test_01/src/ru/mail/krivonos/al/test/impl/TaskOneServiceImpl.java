package ru.mail.krivonos.al.test.impl;

import ru.mail.krivonos.al.test.TaskOneService;

public class TaskOneServiceImpl implements TaskOneService {

    @Override
    public void printSumOrNumber(int xValue, int yValue, int zValue) {
        if (xValue > zValue) {
            System.out.println("X + Y value: " + (xValue + yValue));
        } else {
            System.out.println("Z value: " + zValue);
        }
    }

    @Override
    public void printAverage(int xValue, int yValue, int zValue) {
        int average = (xValue + yValue + zValue) / 3;
        System.out.println("Average: " + average);
    }
}
