package ru.mail.krivonos.project_jd1.servlets.validators.impl;

import ru.mail.krivonos.project_jd1.services.model.xml.XMLItemDTO;
import ru.mail.krivonos.project_jd1.servlets.constants.ValidationConstants;
import ru.mail.krivonos.project_jd1.servlets.validators.XMLItemsDTOValidator;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class XMLItemsDTOValidatorImpl implements XMLItemsDTOValidator {

   private static XMLItemsDTOValidator instance;

    private XMLItemsDTOValidatorImpl() {
    }

    public static XMLItemsDTOValidator getInstance() {
        if (instance == null) {
            synchronized (XMLItemsDTOValidatorImpl.class) {
                if (instance == null) {
                    instance = new XMLItemsDTOValidatorImpl();
                }
            }
        }
        return instance;
    }

    @Override
    public void validate(Map<String, String> messages, Collection<XMLItemDTO> collection) {
        Set<String> uniqueNumbers = new HashSet<>();
        int matchCounter = 1;
        int maxLengthCounter = 0;
        int minLengthCounter = 0;
        for (XMLItemDTO itemDTO : collection) {
            if (!uniqueNumbers.add(itemDTO.getUniqueNumber())) {
                matchCounter++;
                messages.put("match", matchCounter + " items have the same unique number!");
            }
            if (itemDTO.getUniqueNumber().length() > ValidationConstants.UNIQUE_NUMBER_MAX_LENGTH) {
                maxLengthCounter++;
                messages.put("max-length", maxLengthCounter + " items have too long unique number!");
            }
            if (itemDTO.getUniqueNumber().length() == 0) {
                minLengthCounter++;
                messages.put("min-length", minLengthCounter + " items doesn't have unique numbers!");
            }
        }
    }
}
