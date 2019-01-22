package ru.mail.krivonos.al.test.model;

import java.util.Objects;

public class Car {

    private String name;
    private String carModel;
    private int engineCapacity;

    public Car(String name, String carModel, int engineCapacity) {
        this.name = name;
        this.carModel = carModel;
        this.engineCapacity = engineCapacity;
    }

    public String getName() {
        return name;
    }

    public String getCarModel() {
        return carModel;
    }

    public int getEngineCapacity() {
        return engineCapacity;
    }

    @Override
    public String toString() {
        return "Car{" +
                "name='" + name + '\'' +
                ", carModel='" + carModel + '\'' +
                ", engineCapacity=" + engineCapacity +
                '}';
    }
}
