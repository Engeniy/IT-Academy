package ru.mail.krivonos.al.additional.twentyone.multithreading.task;

import ru.mail.krivonos.al.additional.twentyone.multithreading.RandomService;
import ru.mail.krivonos.al.additional.twentyone.multithreading.impl.RandomServiceImpl;
import ru.mail.krivonos.al.additional.twentyone.multithreading.data.SumMaxTaskData;

public class CollectionFiller extends Thread {

    private SumMaxTaskData taskResource;
    private int maxProduce;
    private int range;
    private RandomService randomService = RandomServiceImpl.getInstance();

    public CollectionFiller(SumMaxTaskData taskResource, int maxProduce, int range) {
        this.taskResource = taskResource;
        this.maxProduce = maxProduce;
        this.range = range;
    }

    @Override
    public void run() {
        System.out.println("CollectionFiller started");
        for (int i = 0; i < maxProduce; i++) {
            int element = randomService.getNumber(range);
            taskResource.addElement(element);
        }
        System.out.println("CollectionFiller ended");
    }
}
