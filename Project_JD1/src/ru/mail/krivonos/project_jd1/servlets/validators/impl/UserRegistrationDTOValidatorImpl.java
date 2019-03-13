package ru.mail.krivonos.project_jd1.servlets.validators.impl;

import ru.mail.krivonos.project_jd1.services.model.user.UserRegistrationDTO;
import ru.mail.krivonos.project_jd1.servlets.util.ValidationUtil;
import ru.mail.krivonos.project_jd1.servlets.validators.UserRegistrationDTOValidator;

import java.util.Map;

public class UserRegistrationDTOValidatorImpl implements UserRegistrationDTOValidator {

    private static UserRegistrationDTOValidator instance;

    private UserRegistrationDTOValidatorImpl() {
    }

    public static UserRegistrationDTOValidator getInstance() {
        if (instance == null) {
            synchronized (UserRegistrationDTOValidatorImpl.class) {
                if (instance == null) {
                    instance = new UserRegistrationDTOValidatorImpl();
                }
            }
        }
        return instance;
    }

    @Override
    public void validate(Map<String, String> messages, UserRegistrationDTO userDTO) {
        String email = userDTO.getEmail();
        ValidationUtil.validateEmail(messages, email);
        String name = userDTO.getName();
        ValidationUtil.validateName(messages, name);
        String surname = userDTO.getSurname();
        ValidationUtil.validateSurname(messages, surname);
        String password = userDTO.getPassword();
        ValidationUtil.validatePassword(messages, password);
    }
}
