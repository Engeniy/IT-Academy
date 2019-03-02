package ru.mail.krivonos.project_jd1.repository.impl;

import ru.mail.krivonos.project_jd1.repository.DatabaseInitRepository;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseInitRepositoryImpl implements DatabaseInitRepository {

    private static DatabaseInitRepository instance;

    private DatabaseInitRepositoryImpl() {
    }

    public static DatabaseInitRepository getInstance() {
        if (instance == null) {
            instance = new DatabaseInitRepositoryImpl();
        }
        return instance;
    }

    @Override
    public void runScript(Connection connection, String script) {
        try (Statement statement = connection.createStatement()) {
            statement.execute(script);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
