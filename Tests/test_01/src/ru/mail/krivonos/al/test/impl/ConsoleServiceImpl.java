package ru.mail.krivonos.al.test.impl;

import ru.mail.krivonos.al.test.ConsoleService;

import java.util.Scanner;

public class ConsoleServiceImpl implements ConsoleService {

    private Scanner scanner;

    public ConsoleServiceImpl() {
        scanner = new Scanner(System.in);
    }

    // TODO add validation
    @Override
    public int getNumber(String message) {
        System.out.println(message);
        int input = scanner.nextInt();
        scanner.nextLine();
        return input;
    }

    @Override
    public String getLine(String message) {
        System.out.println(message);
        return scanner.nextLine();
    }
}
