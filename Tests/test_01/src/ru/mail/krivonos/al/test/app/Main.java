package ru.mail.krivonos.al.test.app;

import ru.mail.krivonos.al.test.*;
import ru.mail.krivonos.al.test.impl.*;
import ru.mail.krivonos.al.test.model.Car;
import ru.mail.krivonos.al.test.model.Player;
import ru.mail.krivonos.al.test.model.Student;

import java.io.File;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        ConsoleService consoleService = new ConsoleServiceImpl();

      /*  int xValue = consoleService.getNumber("Input X value:");
        int yValue = consoleService.getNumber("Input Y value:");
        int zValue = consoleService.getNumber("Input Z value:");
        TaskOneService taskOneService = new TaskOneServiceImpl();
        taskOneService.printSumOrNumber(xValue, yValue, zValue);
        taskOneService.printAverage(xValue, yValue, zValue);

        RandomService randomService = new RandomServiceImpl();
        int arraySize = consoleService.getNumber("Input array size:");
        int scopeStart = consoleService.getNumber("Input scope start:");
        int[] array = randomService.getRandomIntArray(arraySize, scopeStart);
        TaskTwoService taskTwoService = new TaskTwoServiceImpl();
        taskTwoService.printMinAndMax(array);
        taskTwoService.printArray(array, "Array before changing:");
        taskTwoService.changeMaxToMultipliedMin(array);
        taskTwoService.printArray(array, "Array after changing:");

        String targetString = consoleService.getLine("Input target string:");
        TaskThreeService taskThreeService = new TaskThreeServiceImpl();
        taskThreeService.printWithoutSomeSymbols(targetString);
*/
        /*int playersAmount = consoleService.getNumber("Input amount of players:");
        int ageFrom = consoleService.getNumber("Input age from:");
        int ageTo = consoleService.getNumber("Input age to:");
        TaskFourService taskFourService = new TaskFourServiceImpl();
        List<Player> players = taskFourService.generatePlayers(playersAmount, ageFrom, ageTo);
        taskFourService.printActivePlayersWithTargetAge(players, 25, 30);

        TaskFiveService taskFiveService = new TaskFiveServiceImpl();
        List<Car> cars = taskFiveService.getCarList(10, 1, 3);
        Map<Integer, List<Car>> map = taskFiveService.groupByEngineCapacity(cars);
        int chosenCapacity = consoleService.getNumber("Input chosen capacity:");
        taskFiveService.printCarGroup(map, chosenCapacity);*/

        File file = new File("src/ru/mail/krivonos/al/test/students.txt");
        TaskSixService taskSixService = new TaskSixServiceImpl();
        taskSixService.fillFileWithStudentsInfo(file, 10);
        List<Student> students = taskSixService.getStudents(file);
        taskSixService.printStudents(students);
    }
}
