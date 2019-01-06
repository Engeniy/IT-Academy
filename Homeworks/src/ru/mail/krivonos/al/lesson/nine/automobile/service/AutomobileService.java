package ru.mail.krivonos.al.lesson.nine.automobile.service;

import ru.mail.krivonos.al.lesson.nine.ConsoleService;
import ru.mail.krivonos.al.lesson.nine.automobile.persistence.Automobile;
import ru.mail.krivonos.al.lesson.nine.automobile.persistence.EnglandAutomobile;
import ru.mail.krivonos.al.lesson.nine.automobile.persistence.GermanAutomobile;
import ru.mail.krivonos.al.lesson.nine.automobile.persistence.RussianAutomobile;

public class AutomobileService {
    ConsoleService consoleService;

    public AutomobileService(ConsoleService consoleService) {
        this.consoleService = consoleService;
    }

    public Automobile[] createAutomobileArray() {
        System.out.println("Input your automobile array size");
        int arraySize = consoleService.inputNumber();

        Automobile[] automobiles = new Automobile[arraySize];
        for (int i = 0; i < automobiles.length; i++) {
            automobiles[i] = createAutomobile();
        }
        return automobiles;
    }

    public Automobile createAutomobile() {
        System.out.println("You are creating automobile. Please input number of automobile you want to create:\n"
                .concat("1. Russian automobile \n2. England automobile \n3.German automobile"));
        int chosenNumber = consoleService.inputNumber();
        switch (chosenNumber) {
            case 1:
                return createRussianAutomobile();
            case 2:
                return createEnglandAutomobile();
            case 3:
                return createGermanAutomobile();
            default:
                System.out.println("Invalid number! Try again!");
                return createAutomobile();
        }
    }

    public RussianAutomobile createRussianAutomobile() {
        System.out.println("You are creating Russian automobile. Input its rate:");
        int rate = consoleService.inputNumber();
        return new RussianAutomobile(rate);
    }

    public EnglandAutomobile createEnglandAutomobile() {
        System.out.println("You are creating England automobile. Input its rate:");
        int rate = consoleService.inputNumber();
        return new EnglandAutomobile(rate);
    }

    public GermanAutomobile createGermanAutomobile() {
        System.out.println("You are creating German automobile. Input its rate:");
        int rate = consoleService.inputNumber();
        return new GermanAutomobile(rate);
    }

    public void printDescription(Automobile automobile) {
        System.out.println(automobile.getDescription());
    }

    public void printDescription(Automobile[] automobiles) {
        for (Automobile automobile : automobiles) {
            printDescription(automobile);
        }
    }
}
