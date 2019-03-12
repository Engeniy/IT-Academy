package ru.mail.krivonos.project_jd1.servlets.validators;

import ru.mail.krivonos.project_jd1.services.model.user.UserInfoDTO;

import java.util.Map;

public interface UserInfoDTOValidator {

    void validate(Map<String, String> messages, UserInfoDTO userDTO);
}
