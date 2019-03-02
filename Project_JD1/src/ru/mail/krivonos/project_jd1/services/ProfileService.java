package ru.mail.krivonos.project_jd1.services;

import ru.mail.krivonos.project_jd1.services.model.ProfileDTO;

public interface ProfileService {

    void add(ProfileDTO profileDTO);

    void update(ProfileDTO profileDTO);

    ProfileDTO getByUserID(Long userID);
}
