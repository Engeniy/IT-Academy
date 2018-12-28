package ru.mail.krivonos.al.lesson.seven;

import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        PrintService printService = new PrintService();

        ConsoleService consoleService = new ConsoleService(scanner, random, printService);
        ArrayService arrayService = new ArrayService(printService);

        System.out.println("Input your array size:");
        int arraySize = consoleService.inputNumber();
        System.out.println("Input elements range border:");
        int range = consoleService.inputNumber();

        int[] rangeArray = consoleService.createArray(arraySize, range);

        System.out.println("Input new elements range border:");
        int newRange = consoleService.inputNumber();
        int[] newRangeArray;

        if (arrayService.isIncludedInRange(range, newRange)) {
            System.out.println("All elements in your array are withing new range and there is no need to create new array.");
        } else {
            System.out.println("We need to copy some elements to create new array with element withing new range.");
            newRangeArray = arrayService.copyValuesFromRangeToArray(rangeArray, newRange);
        }
    }
}
