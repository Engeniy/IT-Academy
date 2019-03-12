package ru.mail.krivonos.project_jd1.servlets.validators;

import ru.mail.krivonos.project_jd1.services.model.item.ItemDTO;

import java.util.Map;

public interface ItemDTOValidator {

    void validate(Map<String, String> messages, ItemDTO itemDTO);
}
