package ru.mail.krivonos.al.additional.thirteen.impl;

import ru.mail.krivonos.al.additional.thirteen.FileService;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FileServiceImpl implements FileService {

    Random random;

    public FileServiceImpl() {
        random = new Random();
    }

    @Override
    public List<String> readFile(File file) {
        List<String> content = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String input = br.readLine();
            while (input != null) {
                content.add(input);
                input = br.readLine();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
        return content;
    }

    @Override
    public void writeWithYears(File file, List<String> content, int[] years) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            for (String aContent : content) {
                bw.write(aContent);
                bw.write(" " + years[random.nextInt(years.length)]);
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
