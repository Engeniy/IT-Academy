package ru.mail.krivonos.project_jd1.servlets.validators.impl;

import ru.mail.krivonos.project_jd1.services.model.user.UserLoginDTO;
import ru.mail.krivonos.project_jd1.servlets.model.Constants;
import ru.mail.krivonos.project_jd1.servlets.validators.UserLoginDTOValidator;

import java.util.Map;

public class UserLoginDTOValidatorImpl implements UserLoginDTOValidator {

    private static UserLoginDTOValidator instance;

    private UserLoginDTOValidatorImpl() {
    }

    public static UserLoginDTOValidator getInstance() {
        if (instance == null) {
            instance = new UserLoginDTOValidatorImpl();
        }
        return instance;
    }

    @Override
    public void validate(Map<String, String> messages, UserLoginDTO userDTO) {
        String email = userDTO.getEmail();
        if (email == null || email.trim().isEmpty()) {
            messages.put("email", "Please enter email!");
        } else if (!email.matches(Constants.EMAIL_VALIDATOR)) {
            messages.put("email", "Invalid email address! Please try again!");
        }
        String password = userDTO.getPassword();
        if (password == null || password.trim().isEmpty()) {
            messages.put("password", "Please enter password!");
        }
    }
}
