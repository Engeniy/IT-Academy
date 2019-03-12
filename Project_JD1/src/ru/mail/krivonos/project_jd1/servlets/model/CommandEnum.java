package ru.mail.krivonos.project_jd1.servlets.model;

public enum CommandEnum {

    LOGIN,
    LOGIN_REDIRECT,
    REGISTRATION,
    REGISTRATION_REDIRECT,
    ITEMS,
    DELETE_ITEM,
    CREATE_ITEM,
    ADD_ITEM,
    UPLOAD,
    ORDERS,
    ORDER,
    UPDATE_STATE,
    CHOOSE_STATE,
    PROFILE,
    PROFILE_UPDATE,
    LOGOUT;

    public static CommandEnum getCommand(String command) {
        try {
            return CommandEnum.valueOf(command.toUpperCase());
        } catch (IllegalArgumentException e) {
            System.out.println("Command not found!");
            e.printStackTrace();
        }
        return null;
    }
}
