package ru.mail.krivonos.al.lesson.twelve.impl;

import ru.mail.krivonos.al.lesson.twelve.ConsoleService;

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

    @Override
    public String inputLine() {
        return scanner.nextLine();
    }

    @Override
    public void fillCollectionWithStrings(Collection<String> collection, int quantity) {
        String input;
        for (int i = 0; i < quantity; i++) {
            System.out.println("Please input next line (" + (quantity - i) + " left):");
            input = inputLine();
            collection.add(input);
        }
    }
}
