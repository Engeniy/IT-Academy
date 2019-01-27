package ru.mail.krivonos.al.lesson.seventeen.task3.task;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class ReadFileTask implements Callable<List<String>> {

    private final String fileName;

    public ReadFileTask(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public List<String> call() throws Exception {
        File file = new File(fileName);
        if (!file.exists()) {
            if (!file.createNewFile()) {
                throw new RuntimeException("Can't create file!");
            }
        }
        List<String> lines = new ArrayList<>();
        synchronized (fileName) {
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                String line = br.readLine();
                while (line != null) {
                    lines.add(line);
                    line = br.readLine();
                }
            }
        }
        return lines;
    }
}
