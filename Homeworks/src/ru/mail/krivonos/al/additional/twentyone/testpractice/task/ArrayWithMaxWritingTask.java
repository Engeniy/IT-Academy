package ru.mail.krivonos.al.additional.twentyone.testpractice.task;

import ru.mail.krivonos.al.additional.twentyone.testpractice.RandomService;
import ru.mail.krivonos.al.additional.twentyone.testpractice.impl.RandomServiceImpl;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ArrayWithMaxWritingTask extends Thread {

    private final String fileName;
    private int arraySize;
    private int range;
    private RandomService randomService = RandomServiceImpl.getInstance();

    public ArrayWithMaxWritingTask(String fileName, int arraySize, int range) {
        this.fileName = fileName;
        this.arraySize = arraySize;
        this.range = range;
    }

    @Override
    public void run() {
        int[] array = randomService.getArray(arraySize, range);
        synchronized (fileName) {
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
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(file, true))) {
                int max = array[0];
                for (int element : array) {
                    bw.write(String.valueOf(element));
                    bw.newLine();
                    max = Math.max(max, element);
                }
                bw.write("Max element: ");
                bw.write(String.valueOf(max));
                bw.newLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
