package ru.mail.krivonos.al.additional.twentyone.multithreading.impl;

import ru.mail.krivonos.al.additional.twentyone.multithreading.ExecutionControlService;
import ru.mail.krivonos.al.additional.twentyone.multithreading.MessageTaskService;
import ru.mail.krivonos.al.additional.twentyone.multithreading.task.MessageTask;

public class MessageTaskServiceImpl implements MessageTaskService {

    @Override
    public void startThreads(Integer threadsAmount) {
        Thread thread = new MessageTask(1, threadsAmount);
        thread.start();
        ExecutionControlService controlService = ExecutionControlServiceImpl.getInstance();
        if (controlService.isReady(threadsAmount)) {
            for (java.lang.Integer i = threadsAmount; i > 0; i--) {
                controlService.execute(i);
            }
        }
        //Just for check, we don't need this method with cycle, but with other implementation it can be useful
        controlService.clear();
    }
}

