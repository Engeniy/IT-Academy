package ru.mail.krivonos.al.lesson.nine.automobile.persistence;

public class EnglandAutomobile extends Automobile {
    private int rate;

    public EnglandAutomobile(int rate) {
        this.rate = rate;
        setCountry("England");
    }

    @Override
    public String getDescription() {
        return super.getDescription().concat("\nMy country - ").concat(getCountry()).concat(". Automobile rating ").concat(String.valueOf(getRate()));
    }

    @Override
    public int getRate() {
        return rate;
    }
}
