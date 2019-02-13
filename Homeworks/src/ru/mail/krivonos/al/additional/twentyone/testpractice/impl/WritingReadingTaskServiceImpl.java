package ru.mail.krivonos.al.additional.twentyone.testpractice.impl;

import ru.mail.krivonos.al.additional.twentyone.testpractice.WritingReadingTaskService;
import ru.mail.krivonos.al.additional.twentyone.testpractice.task.ArrayReadingTask;
import ru.mail.krivonos.al.additional.twentyone.testpractice.task.ArrayWithMaxWritingTask;
import ru.mail.krivonos.al.additional.twentyone.testpractice.task.ArrayWritingTask;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class WritingReadingTaskServiceImpl implements WritingReadingTaskService {

    private static WritingReadingTaskService instance;

    @Override
    public void executeTasks(int tasksNumber, int arraySize, int range, String filePath) {
        int intPostfix = RandomServiceImpl.getInstance().getNumber(100);
        String fileName = filePath + File.separator + intPostfix;
        File file = validateFile(fileName);
        for (int i = 0; i < tasksNumber; i++) {
            Thread thread = new ArrayWithMaxWritingTask(file, arraySize, range);
            thread.start();
        }
    }

    @Override
    public void executeWriteReadTasks(int tasksNumber, int size, int range, String filePath) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        List<Future<Integer>> futureList = new ArrayList<>(size);
        int intPostfix = RandomServiceImpl.getInstance().getNumber(100);
        String fileName = filePath + File.separator + intPostfix;
        File file = validateFile(fileName);
        int lineCounter = 0;
        for (int i = 0; i < tasksNumber; i++) {
            Thread thread = new ArrayWritingTask(file, size, range);
            executorService.submit(thread);
            Callable<Integer> callable = new ArrayReadingTask(file, lineCounter, size);
            Future<Integer> future = executorService.submit(callable);
            futureList.add(future);
            lineCounter += size;
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

    private File validateFile(String fileName) {
        File file = new File(fileName);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
        return file;
    }

    private WritingReadingTaskServiceImpl() {
    }

    public static WritingReadingTaskService getInstance() {
        if (instance == null) {
            instance = new WritingReadingTaskServiceImpl();
        }
        return instance;
    }
}
