package ru.mail.krivonos.al.additional.twentyone.multithreading;

public interface ExecutionControl {

    void unlock();

    boolean finished();
}
