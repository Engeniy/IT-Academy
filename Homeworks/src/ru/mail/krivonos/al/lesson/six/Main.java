package ru.mail.krivonos.al.lesson.six;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ConsoleInputAndRandom consoleInputAndRandom = new ConsoleInputAndRandom();

        System.out.println("Input your number:");
        int targetNumber = scanner.nextInt();
        consoleInputAndRandom.printNumberNumeralsSumAndCounter(targetNumber);
        System.out.println();

        System.out.println("Input array size:");
        int arraySize = scanner.nextInt();
        System.out.println("Input start of scope:");
        int startOfScope = scanner.nextInt();
        System.out.println("Input end of scope:");
        int endOfScope = scanner.nextInt();
        int[] targetArray = consoleInputAndRandom.fillArrayWithRandomNumbers(arraySize, startOfScope, endOfScope);
        int evenCounter = consoleInputAndRandom.countEvenInArray(targetArray);
        System.out.print("Your array contains ");
        System.out.print(evenCounter);
        System.out.println(" even numbers");
        System.out.println();

        System.out.println("Input scope border:");
        int scopeBorder = scanner.nextInt();
        consoleInputAndRandom.printRandomNumberFromScope(scopeBorder);
        System.out.println();

        System.out.println("Input your number:");
        targetNumber = scanner.nextInt();
        consoleInputAndRandom.printNumberNumeralsSumAndCounter2(targetNumber);
    }
}
