package ru.mail.krivonos.al.lesson.six;

import java.util.Random;

public class ConsoleInputAndRandom {
    private Random random = new Random();

    void printNumberNumeralsSumAndCounter(int targetNumber) {
        int numeralsCounter = 0;
        for (int i = targetNumber; i != 0; i /= 10) {
            numeralsCounter++;
        }
        int decimalCounter = 1;
        for (int i = 0; i < numeralsCounter - 1; i++) {
            decimalCounter *= 10;
        }
        int numeralsSum = 0;
        int targetNumeral = 0;
        for (int i = 0; i < numeralsCounter; i++) {
            targetNumeral = targetNumber / decimalCounter;
            if (i == 0) {
                numeralsSum += targetNumeral;
            } else {
                numeralsSum += targetNumeral - ((targetNumeral / 10) * 10);
            }
            decimalCounter /= 10;
        }
        System.out.print("Your number contains ");
        System.out.print(numeralsCounter);
        System.out.print(" numerals and their sum is ");
        System.out.println(numeralsSum);
    }

    private void printArray(int[] targetArray) {
        System.out.println("Your array:");
        for (int element : targetArray) {
            System.out.print(element);
            System.out.print(" ");
        }
        System.out.println();
    }

    int[] fillArrayWithRandomNumbers(int arraySize, int startOfScope, int endOfScope) {
        int[] targetArray = new int[arraySize];
        for (int i = 0; i < arraySize; i++) {
            targetArray[i] = random.nextInt(endOfScope - startOfScope + 1) + startOfScope;
        }
        printArray(targetArray);
        return targetArray;
    }

    int countEvenInArray(int[] targetArray) {
        int evenCounter = 0;
        for (int element : targetArray) {
            if (element % 2 == 0) {
                evenCounter++;
            }
        }
        return evenCounter;
    }

    void printRandomNumberFromScope(int scopeBorder) {
        if (scopeBorder < 0) {
            scopeBorder = -scopeBorder;
        }
        int targetNumber = random.nextInt(2 * scopeBorder + 1) - scopeBorder;
        System.out.println(targetNumber);
    }
}
