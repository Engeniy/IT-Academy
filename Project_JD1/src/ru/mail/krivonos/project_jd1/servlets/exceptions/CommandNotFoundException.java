package ru.mail.krivonos.project_jd1.servlets.exceptions;

public class CommandNotFoundException extends RuntimeException {

    public CommandNotFoundException(String s) {
        super(s);
    }
}
