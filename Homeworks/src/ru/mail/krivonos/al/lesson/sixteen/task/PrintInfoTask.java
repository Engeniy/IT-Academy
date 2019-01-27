package ru.mail.krivonos.al.lesson.sixteen.task;

import ru.mail.krivonos.al.lesson.sixteen.impl.InfoPrintServiceImpl;

public class PrintInfoTask extends Thread {

    private InfoPrintServiceImpl infoPrinter;

    public PrintInfoTask(InfoPrintServiceImpl infoPrinter) {
        this.infoPrinter = infoPrinter;
    }

    @Override
    public void run() {
        infoPrinter.printInfo();
    }
}
