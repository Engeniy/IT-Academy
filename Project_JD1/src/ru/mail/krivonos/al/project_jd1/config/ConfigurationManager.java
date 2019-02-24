package ru.mail.krivonos.al.project_jd1.config;

public interface ConfigurationManager {

    String DATABASE_DRIVER_NAME = "database.driver.name";
    String DATABASE_URL = "database.url";
    String DATABASE_USERNAME = "database.username";
    String DATABASE_PASSWORD = "database.password";
    String DATABASE_SQLINIT = "database.sqlinit";

    String getProperty(String key);
}
