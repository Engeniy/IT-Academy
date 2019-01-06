package ru.mail.krivonos.al.lesson.nine.employee.persistence;

public class SalesPercentEmployee extends Employee {
    private double salesSum;

    private double percentageRatio;

    public SalesPercentEmployee(String firstName, String lastName, double percentageRatio) {
        super(firstName, lastName);
        this.percentageRatio = percentageRatio;
    }

    public SalesPercentEmployee(String firstName, String lastName, double percentageRatio, double salesSum) {
        super(firstName, lastName);
        this.salesSum = salesSum;
        this.percentageRatio = percentageRatio;
    }

    @Override
    public double countSalary() {
        return percentageRatio * salesSum;
    }

    public double getSalesSum() {
        return salesSum;
    }

    public void setSalesSum(double salesSum) {
        this.salesSum = salesSum;
    }

    public double getPercentageRatio() {
        return percentageRatio;
    }

    public void setPercentageRatio(double percentageRatio) {
        this.percentageRatio = percentageRatio;
    }
}
