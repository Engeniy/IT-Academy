package ru.mail.krivonos.project_jd1.repository.connection;

import ru.mail.krivonos.project_jd1.config.ConfigurationManager;
import ru.mail.krivonos.project_jd1.config.ConfigurationManagerImpl;
import ru.mail.krivonos.project_jd1.config.PropertiesVariables;

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
            Class.forName(configurationManager.getProperty(PropertiesVariables.DATABASE_DRIVER_NAME));
            System.out.println("-------- MySQL JDBC Connection Successful --------");
        } catch (ClassNotFoundException e) {
            System.out.println("-------- MySQL JDBC Connection Failed --------");
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public static ConnectionService getInstance() {
        if (instance == null) {
            synchronized (ConnectionServiceImpl.class) {
                if (instance == null) {
                    instance = new ConnectionServiceImpl();
                }
            }
        }
        return instance;
    }

    @Override
    public Connection getConnection() {
        System.out.println("-------- Creating Connection --------");
        Connection connection;
        try {
            Properties properties = new Properties();
            properties.setProperty("user", configurationManager.getProperty(PropertiesVariables.DATABASE_USERNAME));
            properties.setProperty("password", configurationManager.getProperty(PropertiesVariables.DATABASE_PASSWORD));
            connection = DriverManager.getConnection(configurationManager.getProperty(PropertiesVariables.DATABASE_URL), properties);
            System.out.println("-------- Connection Successfully Created --------");
        } catch (SQLException e) {
            System.out.println("-------- Connection Failed --------");
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return connection;
    }
}
