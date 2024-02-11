package org.example.connection;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Provides database {@link Connection} using JDBC
 */
public class DatabaseConnection {
    private static final String URL = Property.getPropertyForConnection().getProperty("db.url");
    private static final String USER_NAME = Property.getPropertyForConnection().getProperty("db.username");
    private static final String PASSWORD = Property.getPropertyForConnection().getProperty("db.password");

    public Connection getConnection() {
        try {
            Class.forName(Property.getPropertyForConnection().getProperty("db.driver")).getDeclaredConstructor()
                    .newInstance();
            Connection connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
            connection.setAutoCommit(false);
            return connection;
        } catch (SQLException | IllegalAccessException | NoSuchMethodException | InstantiationException |
                 InvocationTargetException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
