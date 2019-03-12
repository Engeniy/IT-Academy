package ru.mail.krivonos.project_jd1.servlets.validators;

import ru.mail.krivonos.project_jd1.services.model.user.UserRegistrationDTO;

import java.util.Map;

public interface UserRegistrationDTOValidator {

    void validate(Map<String, String> messages, UserRegistrationDTO userDTO);
}
