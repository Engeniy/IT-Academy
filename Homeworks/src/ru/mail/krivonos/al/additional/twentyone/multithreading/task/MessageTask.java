package ru.mail.krivonos.al.additional.twentyone.multithreading.task;

import ru.mail.krivonos.al.additional.twentyone.multithreading.ExecutionControl;
import ru.mail.krivonos.al.additional.twentyone.multithreading.ExecutionControlService;
import ru.mail.krivonos.al.additional.twentyone.multithreading.impl.ExecutionControlServiceImpl;

public class MessageTask extends Thread implements ExecutionControl {

    private int threadNumber;
    private int threadLimit;
    private boolean unlocked = false;
    private boolean finished = false;
    private final ExecutionControlService controlService = ExecutionControlServiceImpl.getInstance();

    public MessageTask(int threadNumber, int threadLimit) {
        this.threadNumber = threadNumber;
        this.threadLimit = threadLimit;
        Thread.currentThread().setName("Thread " + threadNumber);
    }

    @Override
    public void run() {
        controlService.add(threadNumber, this, threadLimit);
        if (threadNumber < threadLimit) {
            Thread thread = new MessageTask(threadNumber + 1, threadLimit);
            thread.start();
        }
        synchronized (controlService) {
            while (!unlocked) {
                try {
                    controlService.wait();
                } catch (InterruptedException e) {
                    System.out.println(e.getMessage());
                    throw new RuntimeException(e);
                }
            }
            finished = true;
            controlService.notifyAll();
            System.out.println("Hello from Thread " + threadNumber);
        }
    }

    @Override
    public void unlock() {
        unlocked = true;
    }

    @Override
    public boolean finished() {
        return finished;
    }
}
