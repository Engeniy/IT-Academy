package ru.mail.krivonos.al.lesson.nine.employee.persistence;

public class HourlyPaymentEmployee extends Employee {
    private double hoursWorked;

    private double hourPayment;

    public HourlyPaymentEmployee(String firstName, String lastName, double hourPayment) {
        super(firstName, lastName);
        this.hourPayment = hourPayment;
    }

    public HourlyPaymentEmployee(String firstName, String lastName, double hourPayment, double hoursWorked) {
        super(firstName, lastName);
        this.hoursWorked = hoursWorked;
        this.hourPayment = hourPayment;
    }

    @Override
    public double countSalary() {
        return hourPayment * hoursWorked;
    }

    public double getHoursWorked() {
        return hoursWorked;
    }

    public void setHoursWorked(double hoursWorked) {
        this.hoursWorked = hoursWorked;
    }

    public double getHourPayment() {
        return hourPayment;
    }

    public void setHourPayment(double hourPayment) {
        this.hourPayment = hourPayment;
    }
}
