package ru.mail.krivonos.al.lesson.eight;

import java.util.Scanner;

// String for the last task: "На вопрос "Откуда?" - От верблюда! На вопрос "Зачем?" - Затем! На вопрос "Что?"- Цирк Шапито! На вопрос "Как?"- На тебя напал столбняк!"

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ConsoleService consoleService = new ConsoleService(scanner);
        StringService stringService = new StringService();

        System.out.println("Input your string:");
        String inputString = consoleService.inputLine();
        stringService.printLastCharacterIndexes(inputString);
        System.out.println();

        System.out.println("Input number of strings you want to use:");
        int stringsAmount = consoleService.inputNumber();
        String[] checkingStrings = consoleService.inputSeveralLines(stringsAmount);
        System.out.println("Input first conditional word:");
        String firstWordCondition = consoleService.inputLine();
        System.out.println("Input second conditional word:");
        String secondWordCondition = consoleService.inputLine();
        stringService.findStringsContainingTargetWords(checkingStrings, firstWordCondition, secondWordCondition);
        System.out.println();

        System.out.println("Input your string;");
        inputString = consoleService.inputLine();
        stringService.deleteSpacesBetweenQuestionMarks(inputString);
    }
}
