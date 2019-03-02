package ru.mail.krivonos.project_jd1.repository;

import java.sql.Connection;

public interface DatabaseInitRepository {

    void runScript(Connection connection, String script);
}
