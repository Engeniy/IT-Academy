package ru.mail.krivonos.al.project_jd1.impl;

import ru.mail.krivonos.al.project_jd1.ConnectionService;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
public class ConnectionServiceImpl implements ConnectionService {

    private static ConnectionService instance;

    private ConnectionServiceImpl() {
    }

    @Override
    public Connection getConnection() throws SQLException {
        Properties properties = new Properties();
        String fs = File.separator;
        String propertyName = "src" + fs + "ru" + fs + "mail" + fs + "krivonos" + fs + "al" +
                fs + "project_jd1" + fs + "properties" + fs + "database.properties";
        try (FileInputStream fis = new FileInputStream(propertyName)) {
            properties.load(fis);
        } catch (IOException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        String url = properties.getProperty("url");
        String username = properties.getProperty("username");
        String password = properties.getProperty("password");
        return DriverManager.getConnection(url, username, password);
    }

    public static ConnectionService getInstance() {
        if (instance == null) {
            instance = new ConnectionServiceImpl();
        }
        return instance;
    }
}
