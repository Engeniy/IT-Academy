package ru.mail.krivonos.al.lesson.nine.shape.persistence;

public class Rectangle extends Shape {
    private double firstOppositeSides;

    private double secondOppositeSides;

    public Rectangle(double side) {
        this.firstOppositeSides = side;
        this.secondOppositeSides = side;

    }

    public Rectangle(double firstOppositeSides, double secondOppositeSides) {
        this.firstOppositeSides = firstOppositeSides;
        this.secondOppositeSides = secondOppositeSides;
    }

    @Override
    public double calculatePerimeter() {
        return (firstOppositeSides + secondOppositeSides) * 2;
    }

    @Override
    public double calculateSquare() {
        return firstOppositeSides * secondOppositeSides;
    }
}
