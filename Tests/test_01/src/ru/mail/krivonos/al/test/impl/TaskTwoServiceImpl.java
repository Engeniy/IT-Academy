package ru.mail.krivonos.al.test.impl;

import ru.mail.krivonos.al.test.TaskTwoService;

public class TaskTwoServiceImpl implements TaskTwoService {

    @Override
    public void printMinAndMax(int[] array) {
        int min = array[0];
        int max = array[0];
        int minPosition = 0;
        int maxPosition = 0;
        for (int i = 0; i < array.length; i++) {
            if (max < array[i]) {
                max = array[i];
                maxPosition = i;
            }
            if (min > array[i]) {
                min = array[i];
                minPosition = i;
            }
        }
        System.out.println("Max element: " + array[maxPosition]);
        System.out.println("Min element: " + array[minPosition]);
    }

    @Override
    public void changeMaxToMultipliedMin(int[] array) {
        int min = array[0];
        int max = array[0];
        int minPosition = 0;
        int maxPosition = 0;
        for (int i = 0; i < array.length; i++) {
            if (max < array[i]) {
                max = array[i];
                maxPosition = i;
            }
            if (min > array[i]) {
                min = array[i];
                minPosition = i;
            }
        }
       array[maxPosition] = min * max;
    }


    @Override
    public void printArray(int[] array, String message) {
        System.out.println(message);
        for (int element : array) {
            System.out.print(element + " ");
        }
        System.out.println();
    }
}
