package ru.mail.krivonos.al.lesson.seventeen.task3.task;

import ru.mail.krivonos.al.lesson.seventeen.task3.RandomService;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FillFileTask extends Thread {

    private int numbersInLineAmount;
    private int numbersRange;
    private int linesNumber;
    private RandomService randomService;
    private final String fileName;

    public FillFileTask(int numbersInLineAmount, int numbersRange, int linesNumber, RandomService randomService, String fileName) {
        this.numbersInLineAmount = numbersInLineAmount;
        this.numbersRange = numbersRange;
        this.linesNumber = linesNumber;
        this.randomService = randomService;
        this.fileName = fileName;
    }

    @Override
    public void run() {
        File file = new File(fileName);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println(e.getMessage());
                throw new RuntimeException(e);
            }
        }
        synchronized (fileName) {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
                for (int i = 0; i < linesNumber; i++) {
                    String output = randomService.getLine(numbersInLineAmount, numbersRange);
                    bw.write(output);
                    bw.newLine();
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
                throw new RuntimeException(e);
            }
        }
    }
}
