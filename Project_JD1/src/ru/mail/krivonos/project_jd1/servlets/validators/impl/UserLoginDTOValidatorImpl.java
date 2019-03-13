package ru.mail.krivonos.project_jd1.servlets.validators.impl;

import ru.mail.krivonos.project_jd1.services.model.user.UserLoginDTO;
import ru.mail.krivonos.project_jd1.servlets.util.ValidationUtil;
import ru.mail.krivonos.project_jd1.servlets.validators.UserLoginDTOValidator;

import java.util.Map;

public class UserLoginDTOValidatorImpl implements UserLoginDTOValidator {

    private static UserLoginDTOValidator instance;

    private UserLoginDTOValidatorImpl() {
    }

    public static UserLoginDTOValidator getInstance() {
        if (instance == null) {
            synchronized (UserLoginDTOValidatorImpl.class) {
                if (instance == null) {
                    instance = new UserLoginDTOValidatorImpl();
                }
            }
        }
        return instance;
    }

    @Override
    public void validate(Map<String, String> messages, UserLoginDTO userDTO) {
        String email = userDTO.getEmail();
        ValidationUtil.validateEmail(messages, email);
        String password = userDTO.getPassword();
        ValidationUtil.validatePassword(messages, password);
    }
}
