package ru.mail.krivonos.al.additional.twentyone.multithreading.app;

import ru.mail.krivonos.al.additional.twentyone.multithreading.MessageTaskService;
import ru.mail.krivonos.al.additional.twentyone.multithreading.impl.MessageTaskServiceImpl;

public class Main {

    public static void main(String[] args) {
        MessageTaskService messageTaskService = new MessageTaskServiceImpl();
        messageTaskService.startThreads(50);

    }
}
