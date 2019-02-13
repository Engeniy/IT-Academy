package ru.mail.krivonos.al.additional.twentyone.multithreading.data;

import java.util.ArrayDeque;
import java.util.Deque;

public class SumMaxTaskData {

    private int batchSize;
    private final Deque<Integer> deque = new ArrayDeque<>();
    private int sum;
    private int elementsCounter;
    private int maxElements;

    public SumMaxTaskData(int batchSize, int maxElements) {
        this.batchSize = batchSize;
        this.maxElements = maxElements;
    }

    public void addElement(int element) {
        synchronized (deque) {
            deque.add(element);
            if (deque.size() >= batchSize) {
                deque.notify();
            }
        }
        synchronized (this) {
            if (elementsCounter == maxElements/batchSize) {
                this.notify();
            }
        }
    }

    public int[] getElements() {
        synchronized (deque) {
            int[] elements = null;
            try {
                if (deque.size() < batchSize) {
                    deque.wait(1500);
                }
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
                throw new RuntimeException(e);
            }
            if (deque.size() >= batchSize) {
                elements = new int[batchSize];
                for (int i = 0; i < batchSize; i++) {
                    elements[i] = deque.remove();
                }
                elementsCounter++;
            }
            return elements;

        }
    }

    public void sumMax(int maxElement) {
        synchronized (deque) {
            sum += maxElement;
        }
    }

    public int getSum() {
        synchronized (this) {
            if (elementsCounter < maxElements/batchSize) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return sum;
        }
    }
}
