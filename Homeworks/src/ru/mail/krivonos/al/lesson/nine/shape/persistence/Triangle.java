package ru.mail.krivonos.al.lesson.nine.shape.persistence;

public class Triangle extends Shape {
    private double firstSide;

    private double secondSide;

    private double baseSide;

    public Triangle(double commonSide) {
        this.firstSide = commonSide;
        this.secondSide = commonSide;
        this.baseSide = commonSide;
    }

    public Triangle(double twoSides, double baseSide) {
        this.firstSide = twoSides;
        this.secondSide = twoSides;
        this.baseSide = baseSide;
    }

    public Triangle(double firstSide, double secondSide, double baseSide) {
        this.firstSide = firstSide;
        this.secondSide = secondSide;
        this.baseSide = baseSide;
    }

    @Override
    public double calculatePerimeter() {
        return firstSide + secondSide + baseSide;
    }

    @Override
    public double calculateSquare() {
        double halfPerimeter = calculatePerimeter() / 2;
        return Math.sqrt(halfPerimeter * (halfPerimeter - firstSide) * (halfPerimeter - secondSide) * (halfPerimeter - baseSide));
    }
}
