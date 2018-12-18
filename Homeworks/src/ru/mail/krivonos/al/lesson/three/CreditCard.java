package ru.mail.krivonos.al.lesson.three;

public class CreditCard {
    private int accountNumber;
    private double currentAmount;

    public CreditCard() {
    }

    public CreditCard(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public CreditCard(int accountNumber, double currentAmount) {
        this.accountNumber = accountNumber;
        this.currentAmount = currentAmount;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getCurrentAmount() {
        return currentAmount;
    }

    public void setCurrentAmount(double currentAmount) {
        this.currentAmount = currentAmount;
    }

    public void transferToAccount(double transferSum) {
        currentAmount += transferSum;
    }

    // As for me we can't write this method without check, cause currentAmount can't be negative
    public void withdrawFromAccount(double withdrawSum) {
        if (currentAmount - withdrawSum < 0) {
            System.out.println("Illegal withdraw sum! Not enough money!");
            return;
        }
        currentAmount -= withdrawSum;
    }

    public void printCardInfo() {
        System.out.println("Account number: " + accountNumber + "\n" + "Current amount: " + currentAmount);
    }
}
