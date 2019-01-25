package ru.mail.krivonos.al.lesson.sixteen;

import ru.mail.krivonos.al.lesson.sixteen.model.InfoPrinter;

public class InfoPrinterServiceImpl implements InfoPrinterService {

    private InfoPrinter infoPrinter;

    public InfoPrinterServiceImpl(InfoPrinter infoPrinter) {
        this.infoPrinter = infoPrinter;
    }

    @Override
    public void run() {
        infoPrinter.printInfo();
    }
}
