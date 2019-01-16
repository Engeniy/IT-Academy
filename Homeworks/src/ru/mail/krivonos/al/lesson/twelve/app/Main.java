package ru.mail.krivonos.al.lesson.twelve.app;

import ru.mail.krivonos.al.lesson.twelve.ConsoleService;
import ru.mail.krivonos.al.lesson.twelve.ExceptionService;
import ru.mail.krivonos.al.lesson.twelve.MediaBoxService;
import ru.mail.krivonos.al.lesson.twelve.impl.MediaBoxServiceImpl;
import ru.mail.krivonos.al.lesson.twelve.impl.ConsoleServiceImpl;
import ru.mail.krivonos.al.lesson.twelve.impl.ExceptionServiceImpl;
import ru.mail.krivonos.al.lesson.twelve.model.MediaBox;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        ExceptionService exceptionService = new ExceptionServiceImpl();
        try {
            exceptionService.generateNullPointerException();
        } catch (NullPointerException e) {
            System.err.println("NullPointerException message:");
            System.err.println(e.getMessage());
            System.err.println();
        }
        try {
            exceptionService.generateNullPointerException();
        } catch (NullPointerException e) {
            System.err.println("Its not very informative lets print stack trace instead:");
            e.printStackTrace();
        }

        Scanner scanner = new Scanner(System.in);
        ConsoleService consoleService = new ConsoleServiceImpl(scanner);
        boolean finish = false;
        int inputNumber;
        System.out.println("Input 1-3 to see what type of exception you will catch. To exit input 0.");
        while (!finish) {
            inputNumber = consoleService.inputNumber();
            try {
                switch (inputNumber) {
                    case 0:
                        finish = true;
                        break;
                    case 1:
                        exceptionService.chooseThrowingException(inputNumber);
                        break;
                    case 2:
                        exceptionService.chooseThrowingException(inputNumber);
                        break;
                    case 3:
                        exceptionService.chooseThrowingException(inputNumber);
                        break;
                    default:
                        System.out.println("Incorrect choice, please try again!");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        MediaBox mediaBox = new MediaBox();
        MediaBoxService mediaBoxService = new MediaBoxServiceImpl();
        mediaBoxService.decreaseVolume(mediaBox);
        mediaBoxService.decreaseVolume(mediaBox);
        mediaBoxService.increaseVolume(mediaBox);
        mediaBoxService.increaseVolume(mediaBox);
        mediaBoxService.increaseVolume(mediaBox);
    }
}
