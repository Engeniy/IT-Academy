package ru.mail.krivonos.al.project_jd1.app;

import ru.mail.krivonos.al.project_jd1.impl.ConnectionServiceImpl;

import java.sql.Connection;

public class ConnectionTest {
    public static void main(String[] args) {
        try {
            String url = "jdbc:mysql://localhost/TestPr";
            String username = "alex";
            String password = "5518590a";
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try (Connection conn = ConnectionServiceImpl.getInstance().getConnection()) {

                System.out.println("Connection to DB successful!");
            }
        } catch (Exception ex) {
            System.out.println("Connection failed...");
            System.out.println(ex);
        }
    }
}