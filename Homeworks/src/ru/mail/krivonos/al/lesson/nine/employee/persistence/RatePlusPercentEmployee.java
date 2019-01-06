package ru.mail.krivonos.al.lesson.nine.employee.persistence;

public class RatePlusPercentEmployee extends MonthlyRateEmployee {
    private double salesSum;

    private double percentageRatio;

    public RatePlusPercentEmployee(String firstName, String lastName, double monthRate, double percentageRatio) {
        super(firstName, lastName, monthRate);
        this.percentageRatio = percentageRatio;
    }

    public RatePlusPercentEmployee(String firstName, String lastName, double monthRate, double percentageRatio, double salesSum) {
        super(firstName, lastName, monthRate);
        this.salesSum = salesSum;
        this.percentageRatio = percentageRatio;
    }

    @Override
    public double countSalary() {
        return super.countSalary() + percentageRatio * salesSum;
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
