package ru.mail.krivonos.al.lesson.sixteen.app;

import ru.mail.krivonos.al.lesson.sixteen.task.PrintInfoTask;
import ru.mail.krivonos.al.lesson.sixteen.RandomService;
import ru.mail.krivonos.al.lesson.sixteen.impl.RandomServiceImpl;
import ru.mail.krivonos.al.lesson.sixteen.task.ArrayMaxPrintTask;
import ru.mail.krivonos.al.lesson.sixteen.task.ArrayToFileWriteTask;
import ru.mail.krivonos.al.lesson.sixteen.impl.InfoPrintServiceImpl;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        RandomService randomService = new RandomServiceImpl();

        Thread randomArrayUser;
        for (int i = 0; i < 10; i++) {
            randomArrayUser = new ArrayMaxPrintTask("ArrayMaxPrintTask" + i, 10, 50, randomService);
            randomArrayUser.start();
            try {
                randomArrayUser.join();
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
                throw new RuntimeException(e);
            }
        }
        System.out.println("----------------------------------------");

        String fs = File.separator;
        String fileName = "src" + fs + "ru" + fs + "mail" + fs + "krivonos" + fs + "al" + fs + "lesson" + fs +
                "sixteen" + fs + "output.txt";
        for (int i = 0; i < 5; i++) {
            randomArrayUser = new ArrayToFileWriteTask("ArrayToFileWriteTask" + i, 10, 50, randomService, fileName);
            randomArrayUser.start();
        }

        InfoPrintServiceImpl infoPrinter = new InfoPrintServiceImpl();
        Runnable infoPrinterService = new PrintInfoTask(infoPrinter);
        Thread thread;
        for (int i = 0; i < 10; i++) {
            thread = new Thread(infoPrinterService, "InfoPrinterService" + i);
            thread.start();
        }
    }

}
