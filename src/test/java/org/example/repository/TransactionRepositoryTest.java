package org.example.repository;

import org.example.repository.entity.Transaction;
import org.example.repository.entity.TransactionType;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.sql.*;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Unit-testing for TransactionRepository")
@Testcontainers
public class TransactionRepositoryTest {
    @Container
    private static final PostgreSQLContainer<?> postgreSQLContainer =
            new PostgreSQLContainer<>("postgres:16-alpine3.19")
                    .withDatabaseName("test")
                    .withUsername("test")
                    .withPassword("test");

    private static Connection connection;
    @BeforeAll
    public static void setUp() throws SQLException {
        connection = DriverManager.getConnection(postgreSQLContainer.getJdbcUrl(), postgreSQLContainer.getUsername(),
                postgreSQLContainer.getPassword());
        connection.setAutoCommit(false);
        connection.createStatement().execute("CREATE SCHEMA IF NOT EXISTS entity_schema;");
        connection.createStatement().execute("CREATE SEQUENCE IF NOT EXISTS entity_schema.player_sequence;");
        connection.createStatement().execute("CREATE TABLE IF NOT EXISTS entity_schema.player(id INT PRIMARY KEY, " +
                "name VARCHAR(50), password VARCHAR(100), balance INT DEFAULT 100);");
        connection.createStatement().execute("CREATE TABLE IF NOT EXISTS entity_schema.transaction(id INT PRIMARY KEY," +
                "value INT, transaction_type VARCHAR(15), player_id INT REFERENCES entity_schema.player(id))");
        connection.createStatement().execute("CREATE SCHEMA IF NOT EXISTS service_schema;");
        connection.createStatement().execute("CREATE SEQUENCE IF NOT EXISTS service_schema.player_history_sequence;");
        connection.createStatement().execute("CREATE TABLE IF NOT EXISTS service_schema.player_history(" +
                "id INT PRIMARY KEY, player_id INT REFERENCES entity_schema.player(id), what_player_doing VARCHAR(30));");
    }

    @AfterAll
    public static void tearDown() throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }

    @Test
    void shouldProperlyDebitTransaction() {
        //given
        TransactionRepository transactionRepository = new TransactionRepository(connection);
        PlayerRepository playerRepository = new PlayerRepository(connection);
        Transaction transaction = new Transaction(1, 10, TransactionType.DEBIT);
        playerRepository.registrationPlayer("John", "123");

        //when
        int resultValue = transactionRepository.debitTransaction(10, 1);
        int resultValue1 = transactionRepository.debitTransaction(10, 1);
        int resultValue2 = transactionRepository.debitTransaction(100, 2);

        //then
        assertEquals(0, resultValue);
        assertEquals(1, resultValue1);
        assertEquals(2, resultValue2);

        try{
            String sql = "select * from entity_schema.transaction where id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, 1);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                assertEquals(resultSet.getInt("id"), transaction.getId());
                assertEquals(resultSet.getInt("value"), transaction.getValue());
                assertEquals(resultSet.getString("transaction_type"), transaction.getTransactionType().toString());
                assertEquals(resultSet.getInt("player_id"), 1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}