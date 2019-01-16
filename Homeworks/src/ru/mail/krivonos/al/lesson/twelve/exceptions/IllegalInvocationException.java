package ru.mail.krivonos.al.lesson.twelve.exceptions;

public class IllegalInvocationException extends Exception {

    public IllegalInvocationException() {
    }

    public IllegalInvocationException(String s) {
        super(s);
    }

    public IllegalInvocationException(Throwable throwable) {
        super(throwable);
    }
}
