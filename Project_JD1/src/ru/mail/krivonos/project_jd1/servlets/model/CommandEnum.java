package ru.mail.krivonos.project_jd1.servlets.model;

public enum CommandEnum {

    LOGIN,
    REGISTRATION,
    ITEMS,
    ORDERS,
    LOGOUT;

    public static CommandEnum getCommand(String command) {
        return CommandEnum.valueOf(command.toUpperCase());
    }
}
