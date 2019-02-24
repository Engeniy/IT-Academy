package ru.mail.krivonos.al.project_jd1.repository.connection;

import ru.mail.krivonos.al.project_jd1.config.ConfigurationManager;
import ru.mail.krivonos.al.project_jd1.config.ConfigurationManagerImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionServiceImpl implements ConnectionService {

    private static ConnectionService instance;

    private ConfigurationManager configurationManager = ConfigurationManagerImpl.getInstance();

    private ConnectionServiceImpl() {
        System.out.println("-------- MySQL JDBC Connection Testing --------");
        try {
            Class.forName(configurationManager.getProperty(ConfigurationManager.DATABASE_DRIVER_NAME));
            System.out.println("-------- MySQL JDBC Connection Successful --------");
        } catch (ClassNotFoundException e) {
            System.out.println("-------- MySQL JDBC Connection Failed --------");
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public Connection getConnection() {
        System.out.println("-------- Creating Connection --------");
        Connection connection;
        try {
            Properties properties = new Properties();
            properties.setProperty("user", configurationManager.getProperty(ConfigurationManager.DATABASE_USERNAME));
            properties.setProperty("password", configurationManager.getProperty(ConfigurationManager.DATABASE_PASSWORD));
            connection = DriverManager.getConnection(configurationManager.getProperty(ConfigurationManager.DATABASE_URL), properties);
            System.out.println("-------- Connection Successfully Created --------");
        } catch (SQLException e) {
            System.out.println("-------- Connection Failed --------");
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return connection;
    }

    public static ConnectionService getInstance() {
        if (instance == null) {
            instance = new ConnectionServiceImpl();
        }
        return instance;
    }
}
