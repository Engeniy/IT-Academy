package ru.mail.krivonos.project_jd1.servlets.validators.impl;

import ru.mail.krivonos.project_jd1.services.model.ProfileDTO;
import ru.mail.krivonos.project_jd1.servlets.model.Constants;
import ru.mail.krivonos.project_jd1.servlets.validators.ProfileDTOValidator;

import java.util.Map;

public class ProfileDTOValidatorImpl implements ProfileDTOValidator {

    private static ProfileDTOValidator instance;

    private ProfileDTOValidatorImpl() {
    }

    public static ProfileDTOValidator getInstance() {
        if (instance == null) {
            instance = new ProfileDTOValidatorImpl();
        }
        return instance;
    }

    @Override
    public void validate(Map<String, String> messages, ProfileDTO profileDTO) {
        String address = profileDTO.getAddress();
        if (address == null || address.trim().isEmpty()) {
            messages.put("address", "Please enter address!");
        }
        String telephone = profileDTO.getTelephone();
        if (telephone == null || telephone.trim().isEmpty()) {
            messages.put("telephone", "Please enter telephone!");
        } else if (!telephone.matches(Constants.PHONE_NUMBER_VALIDATOR)) {
            messages.put("telephone", "Invalid telephone format!");
        }
    }
}
