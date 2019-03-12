package ru.mail.krivonos.project_jd1.servlets.validators.impl;

import ru.mail.krivonos.project_jd1.services.model.item.ItemDTO;
import ru.mail.krivonos.project_jd1.servlets.model.Constants;
import ru.mail.krivonos.project_jd1.servlets.validators.ItemDTOValidator;

import java.util.Map;

public class ItemDTOValidatorImpl implements ItemDTOValidator {

    private static ItemDTOValidator instance;

    private ItemDTOValidatorImpl() {
    }

    public static ItemDTOValidator getInstance() {
        if (instance == null) {
            instance = new ItemDTOValidatorImpl();
        }
        return instance;
    }

    @Override
    public void validate(Map<String, String> messages, ItemDTO itemDTO) {
        String name = itemDTO.getName();
        if (name == null || name.trim().isEmpty()) {
            messages.put("name", "Please enter name!");
        }
        String description = itemDTO.getDescription();
        if (description == null || description.trim().isEmpty()) {
            messages.put("description", "Please enter description!");
        }
        String uniqueNumber = itemDTO.getUniqueNumber();
        if (uniqueNumber == null || uniqueNumber.trim().isEmpty()) {
            messages.put("uniqueNumber", "Please enter unique number!");
        }
        String price = itemDTO.getPrice();
        if (price == null || price.trim().isEmpty()) {
            messages.put("price", "Please enter price!");
        } else if (!price.matches(Constants.PRICE_VALIDATOR)) {
            messages.put("price", "Illegal price format!");
        }
    }
}
