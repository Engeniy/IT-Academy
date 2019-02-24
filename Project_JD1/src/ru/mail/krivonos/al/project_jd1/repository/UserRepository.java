package ru.mail.krivonos.al.project_jd1.repository;

import ru.mail.krivonos.al.project_jd1.repository.model.User;

import java.sql.Connection;

public interface UserRepository {

    void add(Connection connection, User user);

    void update(Connection connection, User user);

    void deleteByID(Connection connection, Long id);

    User findUserByEmail(Connection connection, String email);
}
