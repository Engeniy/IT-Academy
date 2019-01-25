package ru.mail.krivonos.al.lesson.sixteen.model;

import java.time.LocalTime;

public class InfoPrinter {

    public synchronized void printInfo() {
        LocalTime localTime = LocalTime.now();
        System.out.println(Thread.currentThread().getName() + " Time: " + localTime);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
