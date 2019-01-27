package ru.mail.krivonos.al.lesson.seventeen.task3.app;

import ru.mail.krivonos.al.lesson.seventeen.task3.RandomService;
import ru.mail.krivonos.al.lesson.seventeen.task3.impl.RandomServiceImpl;
import ru.mail.krivonos.al.lesson.seventeen.task3.task.FillFileTask;
import ru.mail.krivonos.al.lesson.seventeen.task3.task.ReadFileTask;
import ru.mail.krivonos.al.lesson.seventeen.task3.task.SumLineNumbersTask;

import java.io.File;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main {

    public static void main(String[] args) {
        String fs = File.separator;
        String fileName = "src" + fs + "ru" + fs + "mail" + fs + "krivonos" + fs + "al" + fs + "lesson" + fs +
                "seventeen" + fs + "task3" + fs + "integers.txt";
        RandomService randomService = new RandomServiceImpl();
        FillFileTask fillFileTask = new FillFileTask(4, 50, 1000, randomService, fileName);
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.submit(fillFileTask);
        ReadFileTask readFileTask = new ReadFileTask(fileName);
        Future<List<String>> submitList = executorService.submit(readFileTask);
        List<String> lines;
        try {
            lines = submitList.get();
        } catch (InterruptedException | ExecutionException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
        List<Future<Integer>> futureIntegers = new LinkedList<>();
        for (int i = 0; i < lines.size() - 10; i += 10) {
            Future<Integer> submit = executorService.submit(new SumLineNumbersTask(lines.subList(i, i + 10)));
            futureIntegers.add(submit);
        }
        executorService.shutdown();

        int max = Integer.MIN_VALUE;
        while (futureIntegers.size() > 0) {
            Iterator<Future<Integer>> iterator = futureIntegers.iterator();
            while (iterator.hasNext()) {
                Future<Integer> integerFuture = iterator.next();
                if (integerFuture.isDone()) {
                    try {
                        max = Math.max(max, integerFuture.get());
                    } catch (InterruptedException | ExecutionException e) {
                        System.out.println(e.getMessage());
                        throw new RuntimeException(e);
                    }
                    iterator.remove();
                }
            }
        }
        System.out.println("Max sum: " + max);
    }
}
