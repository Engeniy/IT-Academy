package ru.mail.krivonos.project_jd1.servlets.util;

import ru.mail.krivonos.project_jd1.servlets.constants.ValidationConstants;

import java.util.Map;

public class ValidationUtil {

    public static void validateName(Map<String, String> messages, String name) {
        if (name == null || name.trim().isEmpty()) {
            messages.put("name", "Please enter name!");
        } else if (name.length() > ValidationConstants.NAME_MAX_LENGTH) {
            messages.put("name", "Name is too long!");
        }
    }

    public static void validateSurname(Map<String, String> messages, String surname) {
        if (surname == null || surname.trim().isEmpty()) {
            messages.put("surname", "Please enter surname!");
        } else if (surname.length() > ValidationConstants.SURNAME_MAX_LENGTH) {
            messages.put("surname", "Surname is too long!");
        }
    }

    public static void validateEmail(Map<String, String> messages, String email) {
        if (email == null || email.trim().isEmpty()) {
            messages.put("email", "Please enter email!");
        } else if (!email.matches(ValidationConstants.EMAIL_VALIDATOR)) {
            messages.put("email", "Invalid email address! Please try again!");
        } else if (email.length() > ValidationConstants.EMAIL_MAX_LENGTH) {
            messages.put("email", "Email is too long!");
        }
    }

    public static void validatePassword(Map<String, String> messages, String password) {
        if (password == null || password.trim().isEmpty()) {
            messages.put("password", "Please enter password!");
        } else if (password.length() > ValidationConstants.PASSWORD_MAX_LENGTH) {
            messages.put("password", "Password is too long!");
        } else if (password.length() < ValidationConstants.PASSWORD_MIN_LENGTH) {
            messages.put("password", "Password is too short!");
        }
    }

    private ValidationUtil() {
    }
}
