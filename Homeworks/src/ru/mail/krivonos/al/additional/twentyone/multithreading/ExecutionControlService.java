package ru.mail.krivonos.al.additional.twentyone.multithreading;

public interface ExecutionControlService {

    void add(int id, ExecutionControl executionControl, int threadsAmount);

    void execute(int id);

    boolean isReady(int threadAmount);

    //We can use it in another implementation
    void clear();
}
