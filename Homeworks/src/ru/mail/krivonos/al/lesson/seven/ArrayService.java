package ru.mail.krivonos.al.lesson.seven;

public class ArrayService {
    private PrintService printService;

    public ArrayService(PrintService printService) {
        this.printService = printService;
    }

    boolean isIncludedInRange(int checkedRangeBorder, int targetRangeBorder) {
        return checkedRangeBorder <= targetRangeBorder;
    }

    int[] copyValuesFromRangeToArray(int[] targetArray, int range) {
        int createdArraySize = countArrayElementsWithinRange(targetArray, range);
        if (createdArraySize == 0) {
            System.out.println("There are no elements that are within specified range in target array!");
            return new int[0];

        }
        int[] createdArray = new int[createdArraySize];
        int positionCounter = 0;

        for (int i = 0; i < targetArray.length; i++) {
            if (targetArray[i] >= -range && targetArray[i] <= range) {
                createdArray[positionCounter++] = targetArray[i];
            }
        }
        printService.printDescription(createdArraySize, range);
        printService.printArray(createdArray);
        return createdArray;
    }

    private int countArrayElementsWithinRange(int[] targetArray, int range) {
        int elementsCounter = 0;

        for (int element : targetArray) {
            if (element >= -range && element <= range) {
                elementsCounter++;
            }
        }
        return elementsCounter;
    }
}
