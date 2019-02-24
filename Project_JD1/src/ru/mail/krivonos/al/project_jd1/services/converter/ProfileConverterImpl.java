package ru.mail.krivonos.al.project_jd1.services.converter;

import ru.mail.krivonos.al.project_jd1.repository.model.Profile;
import ru.mail.krivonos.al.project_jd1.services.model.ProfileDTO;

public class ProfileConverterImpl implements ProfileConverter {

    private static ProfileConverter instance;

    private ProfileConverterImpl() {
    }

    public static ProfileConverter getInstance() {
        if (instance == null) {
            instance = new ProfileConverterImpl();
        }
        return instance;
    }

    @Override
    public Profile fromDTO(ProfileDTO profileDTO) {
        Profile profile = new Profile();
        profile.setUser(profileDTO.getUser());
        profile.setAddress(profileDTO.getAddress());
        profile.setTelephone(profileDTO.getTelephone());
        return profile;
    }

    @Override
    public ProfileDTO toDTO(Profile profile) {
        ProfileDTO profileDTO = new ProfileDTO();
        profileDTO.setUser(profile.getUser());
        profileDTO.setAddress(profile.getAddress());
        profileDTO.setTelephone(profile.getTelephone());
        return profileDTO;
    }
}
