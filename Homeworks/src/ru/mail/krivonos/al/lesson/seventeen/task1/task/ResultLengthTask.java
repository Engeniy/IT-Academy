package ru.mail.krivonos.al.lesson.seventeen.task1.task;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.Callable;

public class ResultLengthTask implements Callable<Long> {

    private String fileName;
    private List<String> lines;

    public ResultLengthTask(String fileName, List<String> lines) {
        this.fileName = fileName;
        this.lines = lines;
    }

    @Override
    public Long call() throws Exception {
        File file = new File(fileName);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println(e.getMessage());
                throw new RuntimeException(e);
            }
        }
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            for (String line : lines) {
                String[] splitted = line.split("\\W");
                bw.write(splitted[splitted.length - 2]);
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
        return file.length();
    }
}
