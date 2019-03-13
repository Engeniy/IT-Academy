package ru.mail.krivonos.project_jd1.servlets.constants;

public class ValidationConstants {

    public static final int NAME_MAX_LENGTH = 31;
    public static final int SURNAME_MAX_LENGTH = 31;
    public static final int EMAIL_MAX_LENGTH = 31;
    public static final int TELEPHONE_MAX_LENGTH = 13;
    public static final int PASSWORD_MAX_LENGTH = 15;
    public static final int PASSWORD_MIN_LENGTH = 4;
    public static final int ADDRESS_MAX_LENGTH = 63;
    public static final int UNIQUE_NUMBER_MAX_LENGTH = 63;
    public static final String EMAIL_VALIDATOR =
            "^([a-z0-9_-]+\\.)*[a-z0-9_-]+@[a-z0-9_-]+(\\.[a-z0-9_-]+)*\\.[a-z]{2,6}$";
    public static final String PHONE_NUMBER_VALIDATOR = "^\\+\\d{12}$";
    public static final String PRICE_VALIDATOR = "^(\\d+\\.)?\\d+$";

    private ValidationConstants() {
    }
}
