package ru.mail.krivonos.al.lesson.thirteen.app;

import ru.mail.krivonos.al.lesson.thirteen.ConsoleService;
import ru.mail.krivonos.al.lesson.thirteen.impl.ConsoleServiceImpl;
import ru.mail.krivonos.al.lesson.thirteen.FileService;
import ru.mail.krivonos.al.lesson.thirteen.impl.FileServiceImpl;

import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        FileService fileService = new FileServiceImpl();
        String fs = File.separator;

        File containingIntArray = new File("src" + fs + "ru" + fs + "mail" + fs + "krivonos" + fs + "al" + fs +
                "lesson" + fs + "thirteen" + fs + "WithIntArray.txt");
        fileService.printFileMinInteger(containingIntArray);

        Scanner scanner = new Scanner(System.in);
        ConsoleService consoleService = new ConsoleServiceImpl(scanner);
        System.out.println("Input number of integers you want to add into array:");
        int integersNumber = consoleService.inputNumber();
        int[] targetArray = new int[integersNumber];
        for (int i = 0; i < targetArray.length; i++) {
            System.out.println("Input next number(" + (targetArray.length - i) + " left):");
            targetArray[i] = consoleService.inputNumber();
        }
        File arrayOutput = new File("src" + fs + "ru" + fs + "mail" + fs + "krivonos" + fs + "al" + fs + "lesson" +
                fs + "thirteen" + fs + "ArrayOutput.txt");
        fileService.writeIntegerArray(arrayOutput, targetArray);

        File withText = new File("src" + fs + "ru" + fs + "mail" + fs + "krivonos" + fs + "al" + fs + "lesson" +
                fs + "thirteen" + fs + "WithText.txt");
        File textOutput = new File("src" + fs + "ru" + fs + "mail" + fs + "krivonos" + fs + "al" + fs + "lesson" +
                fs + "thirteen" + fs + "output" + fs + "CreatedTextOutput.txt");
        fileService.rewriteWithoutWordsFromLengthScope(withText, textOutput, 3, 5);
    }
}
