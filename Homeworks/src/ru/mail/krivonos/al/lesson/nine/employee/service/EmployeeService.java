package ru.mail.krivonos.al.lesson.nine.employee.service;

import ru.mail.krivonos.al.lesson.nine.ConsoleService;
import ru.mail.krivonos.al.lesson.nine.employee.persistence.*;

public class EmployeeService {
    ConsoleService consoleService;

    public EmployeeService(ConsoleService consoleService) {
        this.consoleService = consoleService;
    }

    public Employee[] createEmployeeArray() {
        System.out.println("Input size of employee array you want to create");
        int arraySize = consoleService.inputNumber();

        Employee[] employees = new Employee[arraySize];
        for (int i = 0; i < employees.length; i++) {
            employees[i] = createEmployee();
        }
        return employees;
    }

    public Employee createEmployee() {
        System.out.println("You are creating employee. Please input what type of employee you want to create:\n"
                .concat("1. Hourly payment employee \n2. Monthly rate employee \n")
                .concat("3. Monthly rate employee with sales percent \n4. Sales percent employee"));
        int chosenNumber = consoleService.inputNumber();

        switch (chosenNumber) {
            case 1:
                return createHourlyPaymentEmployee();
            case 2:
                return createMonthlyRateEmployee();
            case 3:
                return createRatePlusPercentEmployee();
            case 4:
                return createSalesPercentEmployee();
            default:
                System.out.println("Incorrect number! Try again!");
                return createEmployee();
        }
    }

    public HourlyPaymentEmployee createHourlyPaymentEmployee() {
        System.out.println("You are creating hourly payment employee. Please input first name: ");
        String firstName = consoleService.inputLine();
        System.out.println("Please input last name: ");
        String lastName = consoleService.inputLine();
        System.out.println("Input hour payment:");
        double hourPayment = consoleService.inputDoubleNumber();
        System.out.println("Input hours worked");
        double hoursWorked = consoleService.inputDoubleNumber();

        return new HourlyPaymentEmployee(firstName, lastName, hourPayment, hoursWorked);
    }

    public MonthlyRateEmployee createMonthlyRateEmployee() {
        System.out.println("You are creating monthly rate employee. Please input first name: ");
        String firstName = consoleService.inputLine();
        System.out.println("Please input last name: ");
        String lastName = consoleService.inputLine();
        System.out.println("Input month rate:");
        double monthRate = consoleService.inputDoubleNumber();

        return new MonthlyRateEmployee(firstName, lastName, monthRate);
    }

    public RatePlusPercentEmployee createRatePlusPercentEmployee() {
        System.out.println("You are creating monthly rate employee with sales percent. Please input first name: ");
        String firstName = consoleService.inputLine();
        System.out.println("Please input last name: ");
        String lastName = consoleService.inputLine();
        System.out.println("Input month rate:");
        double monthRate = consoleService.inputDoubleNumber();
        System.out.println("Input percentage ratio (in format 0.NN):");
        double percentageRatio = consoleService.inputDoubleNumber();
        System.out.println("Input sales sum:");
        double salesSum = consoleService.inputDoubleNumber();

        return new RatePlusPercentEmployee(firstName, lastName, monthRate, percentageRatio, salesSum);
    }

    public SalesPercentEmployee createSalesPercentEmployee() {
        System.out.println("You are creating sales percent employee. Please input first name: ");
        String firstName = consoleService.inputLine();
        System.out.println("Please input last name: ");
        String lastName = consoleService.inputLine();
        System.out.println("Input percentage ratio (in format 0.NN):");
        double percentageRatio = consoleService.inputDoubleNumber();
        System.out.println("Input sales sum:");
        double salesSum = consoleService.inputDoubleNumber();

        return new SalesPercentEmployee(firstName, lastName, percentageRatio, salesSum);
    }

    public void printSalaryPayment(Employee employee) {
        System.out.println("Employee ".concat(employee.getFirstName()).concat(" ").concat(employee.getLastName()).concat(" was paid: ")
                .concat(String.valueOf(employee.countSalary())));
    }

    public void printSalaryPayment(Employee[] employees) {
        for (Employee employee : employees) {
            printSalaryPayment(employee);
        }
    }
}
