package ru.mail.krivonos.project_jd1.repository;

import ru.mail.krivonos.project_jd1.repository.exceptions.UserRepositoryException;
import ru.mail.krivonos.project_jd1.repository.model.User;

import java.sql.Connection;

public interface UserRepository {

    void add(Connection connection, User user) throws UserRepositoryException;

    void updateInfo(Connection connection, User user) throws UserRepositoryException;

    void updateEmail(Connection connection, Long id, String email) throws UserRepositoryException;

    void updatePassword(Connection connection, Long id, String password) throws UserRepositoryException;

    void deleteByID(Connection connection, Long id) throws UserRepositoryException;

    User findUserByEmail(Connection connection, String email) throws UserRepositoryException;

    User findUserByID(Connection connection, Long id) throws UserRepositoryException;
}
