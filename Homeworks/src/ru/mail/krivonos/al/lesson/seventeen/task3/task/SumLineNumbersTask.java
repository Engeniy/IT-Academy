package ru.mail.krivonos.al.lesson.seventeen.task3.task;

import java.util.List;
import java.util.concurrent.Callable;

public class SumLineNumbersTask implements Callable<Integer> {

    private List<String> lines;

    public SumLineNumbersTask(List<String> lines) {
        this.lines = lines;
    }

    @Override
    public Integer call() throws Exception {
        int sum = 0;
        for (String line : lines) {
            String[] numbers = line.split(",");
            for (String number : numbers) {
                sum += Integer.valueOf(number);
            }
        }
        return sum;
    }
}
