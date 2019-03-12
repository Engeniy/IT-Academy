package ru.mail.krivonos.project_jd1.servlets.validators.impl;

import ru.mail.krivonos.project_jd1.services.model.user.UserInfoDTO;
import ru.mail.krivonos.project_jd1.servlets.validators.UserInfoDTOValidator;

import java.util.Map;

public class UserInfoDTOValidatorImpl implements UserInfoDTOValidator {

    private static UserInfoDTOValidator instance;

    private UserInfoDTOValidatorImpl() {
    }

    public static UserInfoDTOValidator getInstance() {
        if (instance == null) {
            instance = new UserInfoDTOValidatorImpl();
        }
        return instance;
    }

    @Override
    public void validate(Map<String, String> messages, UserInfoDTO userDTO) {
        String name = userDTO.getName();
        if (name == null || name.trim().isEmpty()) {
            messages.put("name", "Please enter name!");
        }
        String surname = userDTO.getSurname();
        if (surname == null || surname.trim().isEmpty()) {
            messages.put("surname", "Please enter surname!");
        }
    }
}
