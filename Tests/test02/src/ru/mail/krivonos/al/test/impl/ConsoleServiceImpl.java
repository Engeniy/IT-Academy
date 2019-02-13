package ru.mail.krivonos.al.test.impl;

import ru.mail.krivonos.al.test.ConsoleService;

import java.util.Scanner;

public class ConsoleServiceImpl implements ConsoleService {

    private static ConsoleService instance;
    private Scanner scanner = new Scanner(System.in);

    @Override
    public int getNumber(String message) {
        System.out.println(message);
        int number = scanner.nextInt();
        scanner.nextLine();
        return number;
    }

    private ConsoleServiceImpl() {
    }

    public static ConsoleService getInstance() {
        if (instance == null) {
            instance = new ConsoleServiceImpl();
        }
        return instance;
    }
}
