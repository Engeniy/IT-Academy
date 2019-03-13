package ru.mail.krivonos.project_jd1.servlets.validators;

import ru.mail.krivonos.project_jd1.services.model.xml.XMLItemDTO;
import ru.mail.krivonos.project_jd1.services.model.xml.XMLItemsDTO;

import java.util.Collection;
import java.util.Map;

public interface XMLItemsDTOValidator {

    void validate(Map<String, String> messages, Collection<XMLItemDTO> collection);
}
