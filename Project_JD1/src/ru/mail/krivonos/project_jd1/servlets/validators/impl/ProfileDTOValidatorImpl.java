package ru.mail.krivonos.project_jd1.servlets.validators.impl;

import ru.mail.krivonos.project_jd1.services.model.ProfileDTO;
import ru.mail.krivonos.project_jd1.servlets.constants.ValidationConstants;
import ru.mail.krivonos.project_jd1.servlets.validators.ProfileDTOValidator;

import java.util.Map;

public class ProfileDTOValidatorImpl implements ProfileDTOValidator {

    private static ProfileDTOValidator instance;

    private ProfileDTOValidatorImpl() {
    }

    public static ProfileDTOValidator getInstance() {
        if (instance == null) {
            synchronized (ProfileDTOValidatorImpl.class) {
                if (instance == null) {
                    instance = new ProfileDTOValidatorImpl();
                }
            }
        }
        return instance;
    }

    @Override
    public void validate(Map<String, String> messages, ProfileDTO profileDTO) {
        String address = profileDTO.getAddress();
        if (address == null || address.trim().isEmpty()) {
            messages.put("address", "Please enter address!");
        } else if (address.length() > ValidationConstants.ADDRESS_MAX_LENGTH) {
            messages.put("address", "Address is too long!");
        }
        String telephone = profileDTO.getTelephone();
        if (telephone == null || telephone.trim().isEmpty()) {
            messages.put("telephone", "Please enter telephone!");
        } else if (!telephone.matches(ValidationConstants.PHONE_NUMBER_VALIDATOR)) {
            messages.put("telephone", "Invalid telephone format!");
        } else if (telephone.length() > ValidationConstants.TELEPHONE_MAX_LENGTH) {
            messages.put("telephone", "Address is too long!");
        }
    }
}
