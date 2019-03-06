package ru.mail.krivonos.project_jd1.repository;

import ru.mail.krivonos.project_jd1.repository.exceptions.UserRepositoryException;
import ru.mail.krivonos.project_jd1.repository.model.User;

import java.sql.Connection;

public interface UserRepository {

    void add(Connection connection, User user) throws UserRepositoryException;

    void updateInfo(Connection connection, User user) throws UserRepositoryException;

    void updatePassword(Connection connection, String email, String oldPassword, String newPassword) throws UserRepositoryException;

    User findUserByEmail(Connection connection, String email) throws UserRepositoryException;
}
