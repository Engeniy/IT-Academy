package ru.mail.krivonos.project_jd1.servlets.model;

import java.io.File;

public class Constants {

    public static final String SESSION_USER_KEY = "user-session";
    public static final String DEFAULT_URL = "/dispatcher?command=";
    public static final String MESSAGE_POSTFIX = "&message=";
    public static final String ERROR_POSTFIX = "&error=";
    public static final String XSD_PATH = File.separator + "WEB-INF" + File.separator + "classes" +
            File.separator + "itemschema.xsd";
    public static final String EMAIL_VALIDATOR =
            "^([a-z0-9_-]+\\.)*[a-z0-9_-]+@[a-z0-9_-]+(\\.[a-z0-9_-]+)*\\.[a-z]{2,6}$";
    public static final String PHONE_NUMBER_VALIDATOR = "^\\+\\d{12}$";
    public static final String PRICE_VALIDATOR = "^(\\d*\\.)?\\d+$";

    private Constants() {
    }
}
