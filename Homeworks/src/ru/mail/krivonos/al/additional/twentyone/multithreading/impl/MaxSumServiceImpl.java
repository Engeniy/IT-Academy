package ru.mail.krivonos.al.additional.twentyone.multithreading.impl;

import ru.mail.krivonos.al.additional.twentyone.multithreading.MaxSumService;
import ru.mail.krivonos.al.additional.twentyone.multithreading.data.SumMaxTaskData;
import ru.mail.krivonos.al.additional.twentyone.multithreading.task.CollectionFiller;
import ru.mail.krivonos.al.additional.twentyone.multithreading.task.FindMaxTask;

public class MaxSumServiceImpl implements MaxSumService {

    @Override
    public void printMaxSum(int elementsNumber, int batchSize, int range, int tasksNumber) {
        SumMaxTaskData sumMaxTaskData = new SumMaxTaskData(batchSize, elementsNumber);
        CollectionFiller collectionFiller = new CollectionFiller(sumMaxTaskData, elementsNumber, range);
        collectionFiller.start();
        for (int i = 0; i < tasksNumber; i++) {
            FindMaxTask findMaxTask = new FindMaxTask(sumMaxTaskData);
            findMaxTask.start();
        }
        int sum = sumMaxTaskData.getSum();
        System.out.println("Sum = " + sum);
    }
}
