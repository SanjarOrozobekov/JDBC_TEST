package org.example.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcConfig {

    private final static String url ="jdbc:postgresql://localhost:5432/postgres";
    private final static String user = "postgres";
    private final static String password = "1234";

    public static Connection getConnection() {
        Connection conn = null;
        try{
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connected");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }
}
