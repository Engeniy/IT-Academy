package ru.mail.krivonos.al.additional.twentyone.testpractice.task;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.Callable;

public class ArrayReadingTask implements Callable<Integer> {

    private final File file;
    private int startingLine;
    private int batchSize;

    public ArrayReadingTask(File file, int startingLine, int batchSize) {
        this.file = file;
        this.startingLine = startingLine;
        this.batchSize = batchSize;
    }

    @Override
    public Integer call() {
        synchronized (file) {
            int lineCounter = 0;
            int max = Integer.MIN_VALUE;
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                while (lineCounter < startingLine) {
                    String line = br.readLine();
                    if (line == null) {
                        file.wait(1500);
                    } else {
                        lineCounter++;
                    }
                }
                lineCounter = 0;
                String line = br.readLine();
                while (line == null) {
                    file.wait(1500);
                    line = br.readLine();
                }
                while (lineCounter < batchSize) {
                    int element = Integer.valueOf(line);
                    line = br.readLine();
                    max = Math.max(max, element);
                    lineCounter++;
                }
            } catch (IOException | InterruptedException e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
                throw new RuntimeException(e);
            }
            return max;
        }
    }
}
