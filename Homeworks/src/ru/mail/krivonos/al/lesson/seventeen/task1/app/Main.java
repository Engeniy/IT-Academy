package ru.mail.krivonos.al.lesson.seventeen.task1.app;

import ru.mail.krivonos.al.lesson.seventeen.task1.FileService;
import ru.mail.krivonos.al.lesson.seventeen.task1.impl.FileServiceImpl;
import ru.mail.krivonos.al.lesson.seventeen.task1.task.ResultLengthTask;

import java.io.File;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main {

    public static void main(String[] args) {
        FileService fileService = new FileServiceImpl();
        String fs = File.separator;
        String inputFileName = "src" + fs + "ru" + fs + "mail" + fs + "krivonos" + fs + "al" + fs + "lesson" + fs +
                "seventeen" + fs + "task1" + fs + "files.txt";
        List<String> lines = fileService.readFile(inputFileName);
        String outputFileName = "src" + fs + "ru" + fs + "mail" + fs + "krivonos" + fs + "al" + fs + "lesson" + fs +
                "seventeen" + fs + "task1" + fs + "names.txt";
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        ResultLengthTask resultLengthTask = new ResultLengthTask(outputFileName, lines);
        Future<Long> result = executorService.submit(resultLengthTask);
        executorService.shutdown();
        long resultLength;
        try {
            resultLength = result.get();
        } catch (InterruptedException | ExecutionException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
        System.out.println("Result file length: " + resultLength);
    }
}
