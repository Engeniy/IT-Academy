package ru.mail.krivonos.project_jd1.servlets.validators;

import ru.mail.krivonos.project_jd1.services.model.ProfileDTO;

import java.util.Map;

public interface ProfileDTOValidator {

    void validate(Map<String, String> messages, ProfileDTO profileDTO);
}
