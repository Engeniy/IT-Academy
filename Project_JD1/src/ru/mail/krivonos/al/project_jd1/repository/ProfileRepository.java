package ru.mail.krivonos.al.project_jd1.repository;

import ru.mail.krivonos.al.project_jd1.repository.model.Profile;

import java.sql.Connection;

public interface ProfileRepository {

    void add(Connection connection, Profile profile);

    void update(Connection connection, Profile profile);

    Profile findByUserID(Connection connection, Long userID);
}
