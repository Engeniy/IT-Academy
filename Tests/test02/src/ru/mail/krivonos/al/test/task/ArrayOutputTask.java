package ru.mail.krivonos.al.test.task;

import ru.mail.krivonos.al.test.RandomService;
import ru.mail.krivonos.al.test.impl.RandomServiceImpl;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ArrayOutputTask extends Thread {

    private final File file;
    private int arraySize;
    private String phrase;
    private RandomService randomService = RandomServiceImpl.getInstance();

    public ArrayOutputTask(File file, int arraySize, String phrase) {
        this.file = file;
        this.arraySize = arraySize;
        this.phrase = phrase;
    }

    @Override
    public void run() {
        String[] words = randomService.getRandomWordsArray(arraySize, phrase);
        synchronized (file) {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(file, true))) {
                for (String word : words) {
                    bw.write(word);
                    bw.write(" ");
                }
                bw.newLine();
            } catch (IOException e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
                throw new RuntimeException(e);
            }
            printArray(words);
        }
    }

    private void printArray(String[] words) {
        for (String word : words) {
            System.out.print(word + " ");
        }
        System.out.println();
    }
}
