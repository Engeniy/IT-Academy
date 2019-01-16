package ru.mail.krivonos.al.lesson.twelve.exceptions;

public class IllegalParameterTypeException extends Exception {

    public IllegalParameterTypeException() {
    }

    public IllegalParameterTypeException(String s) {
        super(s);
    }

    public IllegalParameterTypeException(Throwable throwable) {
        super(throwable);
    }
}
