package ru.mail.krivonos.al.lesson.nine;

import ru.mail.krivonos.al.lesson.nine.automobile.persistence.Automobile;
import ru.mail.krivonos.al.lesson.nine.automobile.service.AutomobileService;
import ru.mail.krivonos.al.lesson.nine.employee.persistence.Employee;
import ru.mail.krivonos.al.lesson.nine.employee.service.EmployeeService;
import ru.mail.krivonos.al.lesson.nine.position.persistence.PositionPrintable;
import ru.mail.krivonos.al.lesson.nine.position.service.PositionPrintableService;
import ru.mail.krivonos.al.lesson.nine.shape.persistence.Shape;
import ru.mail.krivonos.al.lesson.nine.shape.service.ShapeService;

import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ConsoleService consoleService = new ConsoleService(scanner);
        Random random = new Random();

        ShapeService shapeService = new ShapeService(random, consoleService);
        Shape[] shapes = shapeService.createRandomShapeArray();
        double perimeterSum = shapeService.sumPerimeter(shapes);
        System.out.println("Sum of perimeters of all shapes in your array: ".concat(String.valueOf(perimeterSum)));
        System.out.println();

        AutomobileService automobileService = new AutomobileService(consoleService);
        Automobile[] automobiles = automobileService.createAutomobileArray();
        automobileService.printDescription(automobiles);
        System.out.println();

        EmployeeService employeeService = new EmployeeService(consoleService);
        Employee[] employees = employeeService.createEmployeeArray();
        employeeService.printSalaryPayment(employees);
        System.out.println();

        PositionPrintableService positionPrintableService = new PositionPrintableService(consoleService);
        PositionPrintable[] positionPrintables = positionPrintableService.createPositionPrintableArray();
        positionPrintableService.printPosition(positionPrintables);
    }
}
