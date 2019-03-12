package ru.mail.krivonos.project_jd1.servlets.validators.impl;

import ru.mail.krivonos.project_jd1.services.model.user.UserRegistrationDTO;
import ru.mail.krivonos.project_jd1.servlets.model.Constants;
import ru.mail.krivonos.project_jd1.servlets.validators.UserRegistrationDTOValidator;

import java.util.Map;

public class UserRegistrationDTOValidatorImpl implements UserRegistrationDTOValidator {

    private static UserRegistrationDTOValidator instance;

    private UserRegistrationDTOValidatorImpl() {
    }

    public static UserRegistrationDTOValidator getInstance() {
        if (instance == null) {
            instance = new UserRegistrationDTOValidatorImpl();
        }
        return instance;
    }

    @Override
    public void validate(Map<String, String> messages, UserRegistrationDTO userDTO) {
        String email = userDTO.getEmail();
        if (email == null || email.trim().isEmpty()) {
            messages.put("email", "Please enter email!");
        } else if (!email.matches(Constants.EMAIL_VALIDATOR)) {
            messages.put("email", "Invalid email address! Please try again!");
        }
        String name = userDTO.getName();
        if (name == null || name.trim().isEmpty()) {
            messages.put("name", "Please enter name!");
        }
        String surname = userDTO.getSurname();
        if (surname == null || surname.trim().isEmpty()) {
            messages.put("surname", "Please enter surname!");
        }
        String password = userDTO.getPassword();
        if (password == null || password.trim().isEmpty()) {
            messages.put("password", "Please enter password!");
        }
    }
}
