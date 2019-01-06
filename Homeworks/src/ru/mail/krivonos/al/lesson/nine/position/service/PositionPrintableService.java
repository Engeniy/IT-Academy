package ru.mail.krivonos.al.lesson.nine.position.service;

import ru.mail.krivonos.al.lesson.nine.ConsoleService;
import ru.mail.krivonos.al.lesson.nine.position.persistence.Accountant;
import ru.mail.krivonos.al.lesson.nine.position.persistence.Headmaster;
import ru.mail.krivonos.al.lesson.nine.position.persistence.PositionPrintable;
import ru.mail.krivonos.al.lesson.nine.position.persistence.Worker;

public class PositionPrintableService {
    ConsoleService consoleService;

    public PositionPrintableService(ConsoleService consoleService) {
        this.consoleService = consoleService;
    }

    public PositionPrintable[] createPositionPrintableArray() {
        System.out.println("Input positions array size:");
        int arraySize = consoleService.inputNumber();

        PositionPrintable[] positionPrintables = new PositionPrintable[arraySize];
        for (int i = 0; i < positionPrintables.length; i++) {
            positionPrintables[i] = createPositionPrintable();
        }
        return positionPrintables;
    }

    public PositionPrintable createPositionPrintable() {
        System.out.println("You are creating position. Please input number of position you want to create: \n"
                .concat("1. Headmaster \n2. Accountant \n3. Worker"));
        int chosenNumber = consoleService.inputNumber();

        switch (chosenNumber) {
            case 1:
                return createHeadmaster();
            case 2:
                return createAccountant();
            case 3:
                return createWorker();
            default:
                System.out.println("Incorrect number! Please try again!");
                return createPositionPrintable();
        }
    }

    public Headmaster createHeadmaster() {
        return new Headmaster();
    }

    public Accountant createAccountant() {
        return new Accountant();
    }

    public Worker createWorker() {
        return new Worker();
    }

    public void printPosition(PositionPrintable positionPrintable) {
        positionPrintable.printPosition();
    }

    public void printPosition(PositionPrintable[] positionPrintables) {
        for (PositionPrintable positionPrintable : positionPrintables) {
            printPosition(positionPrintable);
        }
    }
}
