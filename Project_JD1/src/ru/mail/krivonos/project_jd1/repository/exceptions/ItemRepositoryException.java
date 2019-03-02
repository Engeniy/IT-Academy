package ru.mail.krivonos.project_jd1.repository.exceptions;

public class ItemRepositoryException extends Exception {

    public ItemRepositoryException() {
    }

    public ItemRepositoryException(String s) {
        super(s);
    }

    public ItemRepositoryException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public ItemRepositoryException(Throwable throwable) {
        super(throwable);
    }
}
