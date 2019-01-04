package ru.mail.krivonos.al.lesson.eight;

import java.util.Scanner;

public class ConsoleService {
    private Scanner scanner;

    public ConsoleService(Scanner scanner) {
        this.scanner = scanner;
    }

    int inputNumber() {
        int number = scanner.nextInt();
        scanner.nextLine();
        return number;
    }

    String inputLine() {
        return scanner.nextLine();
    }

    String[] inputSeveralLines(int linesAmount) {
        String[] lines = new String[linesAmount];
        for (int i = 0; i < lines.length; i++) {
            System.out.println("Input next string (".concat(String.valueOf(lines.length - i)).concat(" left):"));
            lines[i] = inputLine();
        }
        return lines;
    }
}
