package ru.mail.krivonos.al.lesson.sixteen.app;

import ru.mail.krivonos.al.lesson.sixteen.InfoPrinterService;
import ru.mail.krivonos.al.lesson.sixteen.InfoPrinterServiceImpl;
import ru.mail.krivonos.al.lesson.sixteen.RandomService;
import ru.mail.krivonos.al.lesson.sixteen.impl.RandomServiceImpl;
import ru.mail.krivonos.al.lesson.sixteen.model.ArrayMaxPrinter;
import ru.mail.krivonos.al.lesson.sixteen.model.ArrayToFileWriter;
import ru.mail.krivonos.al.lesson.sixteen.model.InfoPrinter;
import ru.mail.krivonos.al.lesson.sixteen.model.RandomArrayUser;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        RandomService randomService = new RandomServiceImpl();

        RandomArrayUser randomArrayUser;
        for (int i = 0; i < 10; i++) {
            randomArrayUser = new ArrayMaxPrinter("ArrayMaxPrinter" + i, 10, 50, randomService);
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
            randomArrayUser = new ArrayToFileWriter("ArrayToFileWriter" + i, 10, 50, randomService, fileName);
            randomArrayUser.start();
        }

        InfoPrinter infoPrinter = new InfoPrinter();
        InfoPrinterService infoPrinterService = new InfoPrinterServiceImpl(infoPrinter);
        Thread thread;
        for (int i = 0; i < 10; i++) {
            thread = new Thread(infoPrinterService, "InfoPrinterService" + i);
            thread.start();
        }
    }

}
