package ru.mail.krivonos.al.project_jd1;

import java.sql.Connection;
import java.sql.SQLException;

public interface ConnectionService {

    Connection getConnection() throws SQLException;
}
