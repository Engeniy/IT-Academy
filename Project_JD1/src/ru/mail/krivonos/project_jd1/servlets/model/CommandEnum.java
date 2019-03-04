package ru.mail.krivonos.project_jd1.servlets.model;

public enum CommandEnum {

    LOGIN,
    REGISTRATION,
    ITEMS,
    DELETE_ITEM,
    CREATE_ITEM,
    ADD_ITEM,
    UPLOAD,
    UPLOAD_XML,
    ORDERS,
    ORDER,
    UPDATE_STATE,
    FILTER_STATE,
    PROFILE,
    PROFILE_UPDATE,
    LOGOUT;

    public static CommandEnum getCommand(String command) {
        return CommandEnum.valueOf(command.toUpperCase());
    }
}
