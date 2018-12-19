package ru.mail.krivonos.al.lesson.three;


// I know that I can use float instead of double but for me it's more habitually
public class CardUser {
    public static void main(String[] args) {
        long creditCardNumber = 5143_5546_9547_5643L;
        double creditCardDefaultSum = 56.5d;
        CreditCard creditCard = new CreditCard(creditCardNumber, creditCardDefaultSum);
        double transferSum = 63.6d;
        creditCard.transferSumToAccount(transferSum);
        System.out.println("Credit card status:");
        creditCard.printCardInfo();
        System.out.println();

        long debitCardNumber = 34681_5897_6578_4678L;
        double debitCardDefaultSum = 76.9d;
        CreditCard debitCard = new CreditCard(debitCardNumber, debitCardDefaultSum);
        transferSum = 45.8d;
        debitCard.transferSumToAccount(transferSum);
        System.out.println("Debit card status:");
        debitCard.printCardInfo();
        System.out.println();

        long paymentCardNumber = 8964_7678_9489_0851L;
        double paymentCardDefaultSum = 154.7d;
        CreditCard paymentCard = new CreditCard(paymentCardNumber, paymentCardDefaultSum);
        double withdrawSum = 120.0d;
        paymentCard.withdrawSumFromAccount(withdrawSum);
        System.out.println("Payment card status:");
        paymentCard.printCardInfo();
        System.out.println();

        // Let try to reach negative currentAmount
        paymentCard.withdrawSumFromAccount(withdrawSum);
        System.out.println();
        System.out.println("Payment card status:");
        paymentCard.printCardInfo();
        System.out.println();
    }
}
