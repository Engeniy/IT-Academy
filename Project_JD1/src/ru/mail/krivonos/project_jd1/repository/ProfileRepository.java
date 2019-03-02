package ru.mail.krivonos.project_jd1.repository;

import ru.mail.krivonos.project_jd1.repository.exceptions.ProfileRepositoryException;
import ru.mail.krivonos.project_jd1.repository.model.Profile;

import java.sql.Connection;

public interface ProfileRepository {

    void add(Connection connection, Profile profile) throws ProfileRepositoryException;

    void update(Connection connection, Profile profile) throws ProfileRepositoryException;

    Profile findByUserID(Connection connection, Long userID) throws ProfileRepositoryException;
}
