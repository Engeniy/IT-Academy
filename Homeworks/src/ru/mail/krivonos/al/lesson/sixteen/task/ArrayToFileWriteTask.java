package ru.mail.krivonos.al.lesson.sixteen.task;

import ru.mail.krivonos.al.lesson.sixteen.RandomService;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ArrayToFileWriteTask extends RandomArrayUser {

    private String fileName;

    public ArrayToFileWriteTask(String s, int capacity, int range, RandomService randomService, String fileName) {
        super(s, capacity, range, randomService);
        this.fileName = fileName;
    }

    @Override
    public void run() {
        int[] array = randomService.getIntegersArray(capacity, range);
        synchronized (randomService) {
            File file = validateFile(fileName);
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(file, true))) {
                bw.write(getName() + " ");
                for (int element : array) {
                    bw.write(String.valueOf(element) + " ");
                }
                bw.newLine();
            } catch (IOException e) {
                System.out.println(e.getMessage());
                throw new RuntimeException(e);
            }
        }

    }

    private File validateFile(String fileName) {
        File file = new File(fileName);
        if (!file.exists()) {
            if (!file.mkdirs()) {
                try {
                    file.createNewFile();
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                    throw new RuntimeException(e);
                }
            }
        }
        return file;
    }
}
