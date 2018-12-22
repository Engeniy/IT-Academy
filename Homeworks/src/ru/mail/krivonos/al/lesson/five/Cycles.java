package ru.mail.krivonos.al.lesson.five;

public class Cycles {
    public static void main(String[] args) {
        int startingYear = 2000;
        int endingYear = 2010;
        int daysSum = printDaysAmountInYearsScope(startingYear, endingYear);
        System.out.print("In scope of years from ");
        System.out.print(startingYear);
        System.out.print(" to ");
        System.out.print(endingYear);
        System.out.print(" sum of days is ");
        System.out.println(daysSum);

        System.out.println();

        int startingNumber = 10;
        int endingNumber = 20;
        printSquaresOfNumbersFromScope(startingNumber, endingNumber);

        System.out.println();

        startingNumber = 20;
        endingNumber = 50;
        printNumbersFromScopeByCondition(startingNumber, endingNumber);
    }

    static int printDaysAmountInYearsScope(int startingYear, int endingYear) {
        int daysAmount = 0;
        int daysSum = 0;
        for (int i = startingYear; i <= endingYear; i++) {
            if (i % 4 == 0 && i % 400 != 0) {
                daysAmount = 366;
            } else {
                daysAmount = 365;
            }
            daysSum += daysAmount;
            System.out.print("In year ");
            System.out.print(i);
            System.out.print(" amount of days is ");
            System.out.print(daysAmount);
            System.out.println(" days.");
        }
        return daysSum;
    }

    static void printSquaresOfNumbersFromScope(int startingNumber, int endingNumber) {
        int squareOfNumber = 0;

        for (int i = startingNumber; i <= endingNumber; i++) {
            squareOfNumber = i * i;
            System.out.println(squareOfNumber);
        }
    }

    static void printNumbersFromScopeByCondition (int startingNumber, int endingNumber) {
        for (int i = startingNumber; i < endingNumber; i++) {
            if (i % 3 == 0 && i % 5 != 0) {
                System.out.println(i);
            }
        }
    }
}


