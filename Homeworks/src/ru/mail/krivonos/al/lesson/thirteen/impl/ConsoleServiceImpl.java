package ru.mail.krivonos.al.lesson.thirteen.impl;

import ru.mail.krivonos.al.lesson.thirteen.ConsoleService;

import java.util.Collection;
import java.util.Scanner;

public class ConsoleServiceImpl implements ConsoleService {

    private Scanner scanner;

    public ConsoleServiceImpl(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public int inputNumber() {
        int input = scanner.nextInt();
        scanner.nextLine();
        return input;
    }
}
