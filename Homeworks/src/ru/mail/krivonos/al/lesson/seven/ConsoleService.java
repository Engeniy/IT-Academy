package ru.mail.krivonos.al.lesson.seven;

import java.util.Random;
import java.util.Scanner;

public class ConsoleService {
    private Scanner scanner;
    private Random random;
    private PrintService printService;

    public ConsoleService(Scanner scanner, Random random, PrintService printService) {
        this.scanner = scanner;
        this.random = random;
        this.printService = printService;
    }

    int inputNumber() {
        return scanner.nextInt();
    }

    int[] createArray(int arraySize, int range) {
        int[] targetArray = new int[arraySize];
        for (int i = 0; i < arraySize; i++) {
            targetArray[i] = random.nextInt(2 * range + 1) - range;
        }
        printService.printDescription(arraySize, range);
        printService.printArray(targetArray);
        return targetArray;
    }
}
