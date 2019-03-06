package ru.mail.krivonos.project_jd1.services.exceptions;

public class PasswordChangeException extends Exception {

    public PasswordChangeException() {
    }

    public PasswordChangeException(String s) {
        super(s);
    }

    public PasswordChangeException(Throwable throwable) {
        super(throwable);
    }
}
