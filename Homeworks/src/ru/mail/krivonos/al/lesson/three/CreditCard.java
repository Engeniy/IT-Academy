package ru.mail.krivonos.al.lesson.three;

public class CreditCard {
    private long accountNumber;
    private double currentAmount;

    public CreditCard() {
    }

    public CreditCard(long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public CreditCard(long accountNumber, double currentAmount) {
        this.accountNumber = accountNumber;
        this.currentAmount = currentAmount;
    }

    public long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getCurrentAmount() {
        return currentAmount;
    }

    public void setCurrentAmount(double currentAmount) {
        this.currentAmount = currentAmount;
    }

    public void transferSumToAccount(double transferSum) {
        currentAmount += transferSum;
    }

    // As for me we can't write this method without check, cause currentAmount can't be negative
    public void withdrawSumFromAccount(double withdrawSum) {
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
