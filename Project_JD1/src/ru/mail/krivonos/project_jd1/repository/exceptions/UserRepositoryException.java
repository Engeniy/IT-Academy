package ru.mail.krivonos.project_jd1.repository.exceptions;

public class UserRepositoryException extends Exception {

    public UserRepositoryException() {
    }

    public UserRepositoryException(String s) {
        super(s);
    }

    public UserRepositoryException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public UserRepositoryException(Throwable throwable) {
        super(throwable);
    }
}
