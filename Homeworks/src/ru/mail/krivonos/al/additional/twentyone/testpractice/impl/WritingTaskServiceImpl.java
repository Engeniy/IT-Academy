package ru.mail.krivonos.al.additional.twentyone.testpractice.impl;

import ru.mail.krivonos.al.additional.twentyone.testpractice.WritingTaskService;
import ru.mail.krivonos.al.additional.twentyone.testpractice.task.ArrayReadingTask;
import ru.mail.krivonos.al.additional.twentyone.testpractice.task.ArrayWithMaxWritingTask;
import ru.mail.krivonos.al.additional.twentyone.testpractice.task.ArrayWritingTask;

import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.*;

public class WritingTaskServiceImpl implements WritingTaskService {

    private static WritingTaskService instance;

    @Override
    public void executeTasks(int tasksNumber, int arraySize, int range, String filePath) {
        int intPostfix = RandomServiceImpl.getInstance().getNumber(100);
        for (int i = 0; i < tasksNumber; i++) {
            Thread thread = new ArrayWithMaxWritingTask(filePath + File.separator + intPostfix, arraySize, range);
            thread.start();
        }
    }

    @Override
    public void executeWriteReadTasks(int tasksNumber, int arraySize, int range, String filePath) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        List<Future<Integer>> futureList = new LinkedList<>();
        int intPostfix = RandomServiceImpl.getInstance().getNumber(100);
        String fileName = filePath + File.separator + intPostfix;
        int lineCounter = 0;
        for (int i = 0; i < tasksNumber; i++) {
            Thread thread = new ArrayWritingTask(fileName, arraySize, range);
            executorService.submit(thread);
            Callable<Integer> callable = new ArrayReadingTask(fileName, lineCounter, arraySize);
            Future<Integer> future = executorService.submit(callable);
            futureList.add(future);
            lineCounter += arraySize;
        }
        executorService.shutdown();
        countFutureAverage(futureList);
    }

    private void countFutureAverage(List<Future<Integer>> futureList) {
        int sum = 0;
        for (Future<Integer> integerFuture : futureList) {
            try {
                sum += integerFuture.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        System.out.println(sum / futureList.size());
    }

    private WritingTaskServiceImpl() {
    }

    public static WritingTaskService getInstance() {
        if (instance == null) {
            instance = new WritingTaskServiceImpl();
        }
        return instance;
    }
}
