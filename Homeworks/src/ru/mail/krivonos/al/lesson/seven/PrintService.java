package ru.mail.krivonos.al.lesson.seven;

public class PrintService {
    void printArray(int[] targetArray) {
        System.out.println("Your array:");
        for (int element : targetArray) {
            System.out.print(element);
            System.out.print(" ");
        }
        System.out.println();
    }

    void printDescription(int arraySize, int range) {
        System.out.print("You created array with ");
        System.out.print(arraySize);
        System.out.print(" elements in range from ");
        System.out.print(-range);
        System.out.print(" to ");
        System.out.print(range);
        System.out.println(".");
    }
}
