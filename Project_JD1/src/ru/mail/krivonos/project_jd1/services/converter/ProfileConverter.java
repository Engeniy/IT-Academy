package ru.mail.krivonos.project_jd1.services.converter;

import ru.mail.krivonos.project_jd1.repository.model.Profile;
import ru.mail.krivonos.project_jd1.services.model.ProfileDTO;

public interface ProfileConverter {

    Profile fromDTO(ProfileDTO profileDTO);

    ProfileDTO toDTO(Profile profile);
}
