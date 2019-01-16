package ru.mail.krivonos.al.lesson.twelve.exceptions;

public class DateFormatException extends Exception {
    public DateFormatException() {
    }

    public DateFormatException(String s) {
        super(s);
    }

    public DateFormatException(Throwable throwable) {
        super(throwable);
    }
}
