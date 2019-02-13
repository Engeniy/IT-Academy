package ru.mail.krivonos.al.additional.twentyone.multithreading.task;

import ru.mail.krivonos.al.additional.twentyone.multithreading.data.SumMaxTaskData;

public class FindMaxTask extends Thread {

    private SumMaxTaskData taskResource;
    private static final int CHECK_COUNTER = 3;
    private int checkNumber = 0;

    public FindMaxTask(SumMaxTaskData taskResource) {
        this.taskResource = taskResource;

    }

    @Override
    public void run() {
        while (checkNumber < CHECK_COUNTER) {
            int[] elements = taskResource.getElements();
            if (elements == null) {
                checkNumber++;
                System.out.println("Check number " + checkNumber);
            } else {
                checkNumber = 0;
                int max = elements[0];
                for (int element : elements) {
                    max = Math.max(max, element);
                }
                taskResource.sumMax(max);
            }
        }
    }
}
