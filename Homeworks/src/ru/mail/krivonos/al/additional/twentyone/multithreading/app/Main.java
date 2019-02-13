package ru.mail.krivonos.al.additional.twentyone.multithreading.app;

import ru.mail.krivonos.al.additional.twentyone.multithreading.MaxSumService;
import ru.mail.krivonos.al.additional.twentyone.multithreading.impl.MaxSumServiceImpl;

public class Main {

    public static void main(String[] args) {
        MaxSumService maxSumService = new MaxSumServiceImpl();
        maxSumService.printMaxSum(100, 10, 50, 4);

//        MessageTaskService messageTaskService = new MessageTaskServiceImpl();
//        messageTaskService.startThreads(50);

    }
}
