package ru.mail.krivonos.al.test.impl;

import ru.mail.krivonos.al.test.OutputService;
import ru.mail.krivonos.al.test.task.ArrayOutputTask;

import java.io.File;
import java.io.IOException;

public class OutputServiceImpl implements OutputService {

    private static OutputService instance;

    @Override
    public void outputArray(String fileName, int arraySize, String phrase, int threadsNumbers) {
        File file = validateFile(fileName);
        for (int i = 0; i < threadsNumbers; i++) {
            Thread thread = new ArrayOutputTask(file, arraySize, phrase);
            thread.start();
        }
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

    private OutputServiceImpl() {
    }

    public static OutputService getInstance() {
        if (instance == null) {
            instance = new OutputServiceImpl();
        }
        return instance;
    }
}
