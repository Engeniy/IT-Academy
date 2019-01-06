package ru.mail.krivonos.al.lesson.nine.shape.service;

import ru.mail.krivonos.al.lesson.nine.ConsoleService;
import ru.mail.krivonos.al.lesson.nine.shape.persistence.Circle;
import ru.mail.krivonos.al.lesson.nine.shape.persistence.Rectangle;
import ru.mail.krivonos.al.lesson.nine.shape.persistence.Shape;
import ru.mail.krivonos.al.lesson.nine.shape.persistence.Triangle;

import java.util.Random;

public class ShapeService {
    Random random;
    ConsoleService consoleService;

    public ShapeService(Random random, ConsoleService consoleService) {
        this.random = random;
        this.consoleService = consoleService;
    }

    public Shape[] createRandomShapeArray() {
        System.out.println("Input shapes array size:");
        int arraySize = consoleService.inputNumber();

        Shape[] shapes = new Shape[arraySize];
        for (int i = 0; i < shapes.length; i++) {
            shapes[i] = createRandomShape();
        }
        return shapes;
    }

    public Shape createRandomShape() {
        switch (random.nextInt(3)) {
            case 0:
                return createTriangle();
            case 1:
                return createRectangle();
            case 2:
                return createCircle();
            default:
                return null;
        }
    }

    public Triangle createTriangle() {
        System.out.println("You are trying to create triangle. Please input what type of triangle you want to create:\n"
                .concat("1. Equilateral \n2. Isosceles \n3. Versatile"));
        int chosenNumber = consoleService.inputNumber();
        switch (chosenNumber) {
            case 1:
                return createEquilateralTriangle();
            case 2:
                return createIsoscelesTriangle();
            case 3:
                return createVersatileTriangle();
            default:
                System.out.println("Incorrect number! Please try again!");
                return createTriangle();
        }
    }

    public Rectangle createRectangle() {
        System.out.println("You are trying to create rectangle. Please input what type of rectangle you want to create:\n"
                .concat("1. Non square rectangle \n2. Square"));
        int chosenNumber = consoleService.inputNumber();
        switch (chosenNumber) {
            case 1:
                return createNonSquareRectangle();
            case 2:
                return createSquare();
            default:
                System.out.println("Incorrect number! Please try again!");
                return createRectangle();
        }
    }

    public Triangle createEquilateralTriangle() {
        System.out.println("You are creating equilateral triangle. Please input side length:");
        double sideLength = consoleService.inputDoubleNumber();

        return new Triangle(sideLength);
    }

    public Triangle createIsoscelesTriangle() {
        System.out.println("You are creating isosceles triangle. Please input side length:");
        double sideLength = consoleService.inputDoubleNumber();
        System.out.println("Please input base length:");
        double baseLength = consoleService.inputDoubleNumber();

        return new Triangle(sideLength, baseLength);
    }

    public Triangle createVersatileTriangle() {
        System.out.println("You are creating versatile triangle. Please input first side length:");
        double firstSide = consoleService.inputDoubleNumber();
        System.out.println("Please input second side length");
        double secondSide = consoleService.inputDoubleNumber();
        System.out.println("Please input base side length");
        double baseSide = consoleService.inputDoubleNumber();

        return new Triangle(firstSide, secondSide, baseSide);
    }

    public Rectangle createNonSquareRectangle() {
        System.out.println("You are creating rectangle. Please input first opposite sides length:");
        double firstOppositeSides = consoleService.inputDoubleNumber();
        System.out.println("Please input second opposite sides length:");
        double secondOppositeSides = consoleService.inputDoubleNumber();

        return new Rectangle(firstOppositeSides, secondOppositeSides);
    }

    public Rectangle createSquare() {
        System.out.println("You are creating square. Please input side length:");
        double side = consoleService.inputDoubleNumber();

        return new Rectangle(side);
    }

    public Circle createCircle() {
        System.out.println("You are creating circle. Please input radius:");
        double radius = consoleService.inputDoubleNumber();

        return new Circle(radius);
    }

    public double sumPerimeter(Shape[] shapes) {
        double perimeterSum = 0;
        for (Shape shape : shapes) {
            perimeterSum += shape.calculatePerimeter();
        }
        return perimeterSum;
    }
}
