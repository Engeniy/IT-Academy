package ru.mail.krivonos.al.lesson.nine.automobile.persistence;

public class RussianAutomobile extends Automobile {
    private int rate;

    public RussianAutomobile(int rate) {
        this.rate = rate;
        setCountry("Russia");
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
