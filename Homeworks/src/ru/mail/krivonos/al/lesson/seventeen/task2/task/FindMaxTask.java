package ru.mail.krivonos.al.lesson.seventeen.task2.task;

import ru.mail.krivonos.al.lesson.seventeen.task2.RandomService;

import java.util.List;
import java.util.concurrent.Callable;

public class FindMaxTask implements Callable<Integer> {

    private RandomService randomService;
    private int size;
    private int range;

    public FindMaxTask(RandomService randomService, int size, int range) {
        this.randomService = randomService;
        this.size = size;
        this.range = range;
    }

    @Override
    public Integer call() throws Exception {
        List<Integer> integers = randomService.getIntegers(size, range);
        int max = integers.get(0);
        for (Integer integer : integers) {
            max = Math.max(max, integer);
        }
        return max;
    }
}
