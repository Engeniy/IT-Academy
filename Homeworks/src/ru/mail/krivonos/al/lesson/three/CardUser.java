package ru.mail.krivonos.al.lesson.three;


// I know that I can use float instead of double but for me it's more habitually
public class CardUser {
    public static void main(String[] args) {
        CreditCard creditCard = new CreditCard(514355, 56.5d);
        double transferSum = 63.6d;
        creditCard.transferToAccount(transferSum);
        System.out.println("Credit card status:");
        creditCard.printCardInfo();
        System.out.println();

        CreditCard debitCard = new CreditCard(346815, 76.9d);
        transferSum = 45.8d;
        debitCard.transferToAccount(transferSum);
        System.out.println("Debit card status:");
        debitCard.printCardInfo();
        System.out.println();

        CreditCard paymentCard = new CreditCard(896476, 154.7d);
        double withdrawSum = 120.0d;
        paymentCard.withdrawFromAccount(withdrawSum);
        System.out.println("Payment card status:");
        paymentCard.printCardInfo();
        System.out.println();

        // Let try to reach negative currentAmount
        paymentCard.withdrawFromAccount(withdrawSum);
        System.out.println("Payment card status:");
        paymentCard.printCardInfo();
        System.out.println();
    }
}
