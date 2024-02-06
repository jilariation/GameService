package org.example.repository.dto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Provides database {@link Connection} using JDBC
 */
public class DatabaseConnection {
    private static final String URL = Property.getProperty().getProperty("db.url");
    private static final String USER_NAME = Property.getProperty().getProperty("db.username");
    private static final String PASSWORD = Property.getProperty().getProperty("db.password");

    public Connection getConnection() {
        try {
            Connection connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
            connection.setAutoCommit(false);
            return connection;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
