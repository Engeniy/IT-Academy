package ru.mail.krivonos.al.additional.twentyone.multithreading.impl;

import ru.mail.krivonos.al.additional.twentyone.multithreading.ExecutionControl;
import ru.mail.krivonos.al.additional.twentyone.multithreading.ExecutionControlService;

import java.util.HashMap;
import java.util.Map;

public class ExecutionControlServiceImpl implements ExecutionControlService {

    private static ExecutionControlService instance;
    private Map<Integer, ExecutionControl> executables = new HashMap<>();

    private ExecutionControlServiceImpl() {
    }

    @Override
    public void add(int id, ExecutionControl executionControl, int threadsAmount) {
        synchronized (this) {
            executables.put(id, executionControl);
            if (executables.size() == threadsAmount) {
                this.notifyAll();
            }
        }
    }

    @Override
    public void execute(int id) {
        synchronized (this) {
            ExecutionControl executionControl = executables.get(id);
            executionControl.unlock();
            this.notifyAll();
            while (!executionControl.finished()) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    System.out.println(e.getMessage());
                    throw new RuntimeException(e);
                }
            }
        }
    }

    @Override
    public boolean isReady(int threadAmount) {
        synchronized (this) {
            while (threadAmount != executables.size()) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    System.out.println(e.getMessage());
                    throw new RuntimeException(e);
                }
            }
        }
        return threadAmount == executables.size();
    }

    public static ExecutionControlService getInstance() {
        if (instance == null) {
            synchronized (ExecutionControlService.class) {
                if (instance == null) {
                    instance = new ExecutionControlServiceImpl();
                }
            }
        }
        return instance;
    }

    @Override
    public void clear() {
        executables.clear();
    }
}
