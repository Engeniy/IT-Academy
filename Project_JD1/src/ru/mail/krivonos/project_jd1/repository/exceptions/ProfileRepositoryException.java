package ru.mail.krivonos.project_jd1.repository.exceptions;

public class ProfileRepositoryException extends Exception {

    public ProfileRepositoryException() {
    }

    public ProfileRepositoryException(String s) {
        super(s);
    }

    public ProfileRepositoryException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public ProfileRepositoryException(Throwable throwable) {
        super(throwable);
    }
}
