package ru.mail.krivonos.al.lesson.seventeen.task2.app;

import ru.mail.krivonos.al.lesson.seventeen.task2.RandomService;
import ru.mail.krivonos.al.lesson.seventeen.task2.impl.RandomServiceImpl;
import ru.mail.krivonos.al.lesson.seventeen.task2.task.FindMaxTask;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main {

    public static void main(String[] args) {
        RandomService randomService = new RandomServiceImpl();
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        List<Future<Integer>> futureList = new LinkedList<>();
        for (int i = 0; i < 10; i++) {
            Future<Integer> maxElement = executorService.submit(new FindMaxTask(randomService, 10, 50));
            futureList.add(maxElement);
        }
        executorService.shutdown();
        int sum = 0;
        int size = futureList.size();
        while (futureList.size() > 0) {
            Iterator<Future<Integer>> iterator = futureList.iterator();
            while (iterator.hasNext()) {
                Future<Integer> future = iterator.next();
                if (future.isDone()) {
                    try {
                        sum += future.get();
                    } catch (InterruptedException | ExecutionException e) {
                        System.out.println(e.getMessage());
                        throw new RuntimeException(e);
                    }
                    iterator.remove();
                }
            }
        }
        System.out.println("Max elements average: " + (sum / size));
    }
}
