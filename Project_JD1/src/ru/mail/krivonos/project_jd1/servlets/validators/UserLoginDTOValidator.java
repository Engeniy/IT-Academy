package ru.mail.krivonos.project_jd1.servlets.validators;

import ru.mail.krivonos.project_jd1.services.model.user.UserLoginDTO;

import java.util.Map;

public interface UserLoginDTOValidator {

    void validate(Map<String, String> messages, UserLoginDTO userDTO);
}
