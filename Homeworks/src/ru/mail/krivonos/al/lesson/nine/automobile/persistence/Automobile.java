package ru.mail.krivonos.al.lesson.nine.automobile.persistence;

public abstract class Automobile {
    private String country;

    public String getDescription() {
        return "Automobile: ";
    }

    public abstract int getRate();

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
