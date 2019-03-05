package ru.mail.krivonos.project_jd1.services.exceptions;

public class RegistrationException extends Exception {

    public RegistrationException() {
    }

    public RegistrationException(String s) {
        super(s);
    }

    public RegistrationException(Throwable throwable) {
        super(throwable);
    }
}
