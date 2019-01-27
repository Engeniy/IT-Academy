package ru.mail.krivonos.al.lesson.seventeen.task1.impl;

import ru.mail.krivonos.al.lesson.seventeen.task1.FileService;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileServiceImpl implements FileService {

    @Override
    public List<String> readFile(String fileName) {
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
        List<String> lines = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line = br.readLine();
            while (line != null) {
                lines.add(line);
                line = br.readLine();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
        return lines;
    }
}
