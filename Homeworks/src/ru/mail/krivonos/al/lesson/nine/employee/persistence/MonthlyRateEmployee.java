package ru.mail.krivonos.al.lesson.nine.employee.persistence;

public class MonthlyRateEmployee extends Employee {
    private double monthRate;

    public MonthlyRateEmployee(String firstName, String lastName, double monthRate) {
        super(firstName, lastName);
        this.monthRate = monthRate;
    }

    @Override
    public double countSalary() {
        return monthRate;
    }

    public double getMonthRate() {
        return monthRate;
    }

    public void setMonthRate(double monthRate) {
        this.monthRate = monthRate;
    }
}
