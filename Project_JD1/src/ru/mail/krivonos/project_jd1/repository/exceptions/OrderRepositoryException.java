package ru.mail.krivonos.project_jd1.repository.exceptions;

public class OrderRepositoryException extends Exception {

    public OrderRepositoryException(String s) {
        super(s);
    }

    public OrderRepositoryException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public OrderRepositoryException(Throwable throwable) {
        super(throwable);
    }

}
