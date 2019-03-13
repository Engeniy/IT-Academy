package ru.mail.krivonos.project_jd1.servlets.validators.impl;

import ru.mail.krivonos.project_jd1.services.model.item.ItemDTO;
import ru.mail.krivonos.project_jd1.servlets.constants.ValidationConstants;
import ru.mail.krivonos.project_jd1.servlets.util.ValidationUtil;
import ru.mail.krivonos.project_jd1.servlets.validators.ItemDTOValidator;

import java.util.Map;

public class ItemDTOValidatorImpl implements ItemDTOValidator {

    private static ItemDTOValidator instance;

    private ItemDTOValidatorImpl() {
    }

    public static ItemDTOValidator getInstance() {
        if (instance == null) {
            synchronized (ItemDTOValidatorImpl.class) {
                if (instance == null) {
                    instance = new ItemDTOValidatorImpl();
                }
            }
        }
        return instance;
    }

    @Override
    public void validate(Map<String, String> messages, ItemDTO itemDTO) {
        String name = itemDTO.getName();
        ValidationUtil.validateName(messages, name);
        String description = itemDTO.getDescription();
        if (description == null || description.trim().isEmpty()) {
            messages.put("description", "Please enter description!");
        }
        String uniqueNumber = itemDTO.getUniqueNumber();
        if (uniqueNumber == null || uniqueNumber.trim().isEmpty()) {
            messages.put("uniqueNumber", "Please enter unique number!");
        } else if (uniqueNumber.length() > ValidationConstants.UNIQUE_NUMBER_MAX_LENGTH) {
            messages.put("uniqueNumber", "Unique number is too long!");
        }
        String price = itemDTO.getPrice();
        if (price == null || price.trim().isEmpty()) {
            messages.put("price", "Please enter price!");
        } else if (!price.matches(ValidationConstants.PRICE_VALIDATOR)) {
            messages.put("price", "Illegal price format!");
        }
    }
}
