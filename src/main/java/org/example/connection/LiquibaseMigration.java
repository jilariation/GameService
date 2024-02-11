package org.example.connection;

import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.LiquibaseException;
import liquibase.resource.ClassLoaderResourceAccessor;

import java.sql.Connection;

/**
 * Makes migrations work using {@link Liquibase}
 */
public class LiquibaseMigration {
    public static void getMigrations() {
        Connection connection = new DatabaseConnection().getConnection();
        try {
            Database database = DatabaseFactory.getInstance().findCorrectDatabaseImplementation(
                    new JdbcConnection(connection));

            Liquibase liquibase = new Liquibase(Property.getPropertyForLiquibase().getProperty("liquibase.changelog"),
                    new ClassLoaderResourceAccessor(), database);
            liquibase.update();
        } catch (LiquibaseException e) {
            throw new RuntimeException(e);
        }
    }
}
