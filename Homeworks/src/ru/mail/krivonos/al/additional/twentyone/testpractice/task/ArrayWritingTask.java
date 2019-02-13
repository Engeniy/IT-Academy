package ru.mail.krivonos.al.additional.twentyone.testpractice.task;

import ru.mail.krivonos.al.additional.twentyone.testpractice.RandomService;
import ru.mail.krivonos.al.additional.twentyone.testpractice.impl.RandomServiceImpl;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class ArrayWritingTask extends Thread {
    private final File file;
    private int size;
    private int range;
    private RandomService randomService = RandomServiceImpl.getInstance();

    public ArrayWritingTask(File file, int size, int range) {
        this.file = file;
        this.size = size;
        this.range = range;
    }

    @Override
    public void run() {
        List<Integer> list = randomService.getList(size, range);
        synchronized (file) {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(file, true))) {
                for (int element : list) {
                    bw.write(String.valueOf(element));
                    bw.newLine();
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
                throw new RuntimeException(e);
            }
            file.notifyAll();
        }
    }
}
