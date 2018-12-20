package ru.mail.krivonos.al.lesson.four;

public class ArraysAndConditions {
    public static void main(String[] args) {
        int[] firstFourElementsArray = {1, 2, 3, 4};
        int[] secondFourElementsArray = {5, 6, 7, 8};
        int[] eightElementsArray = new int[8];
        eightElementsArray[0] = firstFourElementsArray[0];
        eightElementsArray[1] = firstFourElementsArray[1];
        eightElementsArray[2] = firstFourElementsArray[2];
        eightElementsArray[3] = firstFourElementsArray[3];
        eightElementsArray[4] = secondFourElementsArray[0];
        eightElementsArray[5] = secondFourElementsArray[1];
        eightElementsArray[6] = secondFourElementsArray[2];
        eightElementsArray[7] = secondFourElementsArray[3];
        /*Or we can write it in such way:
        int[] eightElementArray1 = {firstFourElementsArray[0], firstFourElementsArray[1], firstFourElementsArray[2], firstFourElementsArray[3],
        secondFourElementsArray[0], secondFourElementsArray[1], secondFourElementsArray[2], secondFourElementsArray[3]};
        But previous variant more readable*/

        System.out.println(eightElementsArray[7]);

        int yearToCountDays = 1600;
        System.out.println("There is " + countDaysInYear(yearToCountDays) + " days in year " + yearToCountDays);
        yearToCountDays = 1604;
        System.out.println("There is " + countDaysInYear(yearToCountDays) + " days in year " + yearToCountDays);
        yearToCountDays = 2011;
        System.out.println("There is " + countDaysInYear(yearToCountDays) + " days in year " + yearToCountDays);

        int seasonIndex = 2;
        printSeasonByIndex(seasonIndex);
        seasonIndex = 4;
        printSeasonByIndex(seasonIndex);
    }

    public static int countDaysInYear(int yearToCountDays) {
        if (yearToCountDays % 4 == 0 && yearToCountDays % 400 != 0) {
            return 366;
        } else {
            return 365;
        }
    }

    public static void printSeasonByIndex(int seasonIndex) {
        switch (seasonIndex) {
            case 0:
                System.out.println("It is Winter!");
                break;
            case 1:
                System.out.println("It is Spring!");
                break;
            case 2:
                System.out.println("It is Summer!");
                break;
            case 3:
                System.out.println("It is Autumn!");
                break;
            default:
                System.out.println("There is no such season! Try to use index in scope 0-3!");
        }
    }
}
