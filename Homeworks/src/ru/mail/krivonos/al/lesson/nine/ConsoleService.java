package ru.mail.krivonos.al.lesson.nine;

import java.util.Scanner;

public class ConsoleService {
    private Scanner scanner;

    public ConsoleService(Scanner scanner) {
        this.scanner = scanner;
    }

    public int inputNumber() {
        int number = scanner.nextInt();
        scanner.nextLine();
        return number;
    }

    public double inputDoubleNumber() {
        double number = scanner.nextDouble();
        scanner.nextLine();
        return number;
    }

    public String inputLine() {
        return scanner.nextLine();
    }
}
