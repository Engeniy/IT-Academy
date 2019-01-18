package ru.mail.krivonos.al.lesson.thirteen.impl;

import ru.mail.krivonos.al.lesson.thirteen.FileService;

import java.io.*;
import java.util.*;

public class FileServiceImpl implements FileService {
    @Override
    public void printFileMinInteger(File targetFile) {
        if (!targetFile.exists()) {
            validateFile(targetFile);
        }
        List<Integer> integers = new ArrayList<>();
        try (Scanner scanner = new Scanner(targetFile)) {
            while (scanner.hasNext()) {
                if (scanner.hasNextInt()) {
                    integers.add(scanner.nextInt());
                } else {
                    scanner.next();
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }

        int min = Integer.MAX_VALUE;
        for (Integer integer : integers) {
            min = Math.min(min, integer);
        }
        System.out.println("Minimal integer: " + min);
    }

    @Override
    public void writeIntegerArray(File targetFile, int[] targetArray) {
        if (!targetFile.exists()) {
            validateFile(targetFile);
        }
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(targetFile))) {
            for (int element : targetArray) {
                bw.write(String.valueOf(element));
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public void rewriteWithoutWordsFromLengthScope(File input, File output, int lengthScopeStart, int lengthScopeEnd) {
        if (!input.exists()) {
            validateFile(input);
        }
        if (!output.exists()) {
            validateFile(output);
        }

        try (BufferedReader br = new BufferedReader(new FileReader(input));
             BufferedWriter bw = new BufferedWriter(new FileWriter(output))) {

            StringBuilder stringBuilder = new StringBuilder();
            String inputString = br.readLine();
            while (inputString != null) {
                stringBuilder.append(inputString);
                stringBuilder.append(System.lineSeparator());
                inputString = br.readLine();
            }
            String wholeInput = stringBuilder.toString();
            Set<String> words = new HashSet<>();
            for (String word : wholeInput.split("\\W")) {
                words.add(word);
            }
            for (String word : words) {
                if (word.length() >= lengthScopeStart && word.length() <= lengthScopeEnd) {
                    wholeInput = wholeInput.replace(word, "");
                }
            }
            bw.write(wholeInput);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void validateFile(File file) {
        String[] directories = file.getAbsolutePath().split(File.separator);
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < directories.length - 1; i++) {
            stringBuilder.append(File.separator);
            stringBuilder.append(directories[i]);
        }
        File path = new File(stringBuilder.toString());
        if (!path.exists()) {
            if (!path.mkdirs()) {
                throw new RuntimeException("Can't create file path!");
            }
        }
        try {
            if (!file.createNewFile()) {
                throw new RuntimeException("Can't create file!");
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
