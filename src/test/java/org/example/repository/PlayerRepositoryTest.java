package org.example.repository;

import org.example.repository.entity.Player;
import org.example.repository.entity.PlayerHistory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.sql.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Unit-testing for PlayerRepository")
@Testcontainers
public class PlayerRepositoryTest {
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
    void shouldProperlyRegistrationPlayer() {
        //given
        PlayerRepository playerRepository = new PlayerRepository(connection);
        String testName = "John";
        String testPassword = "123";

        //when
        playerRepository.registrationPlayer(testName, testPassword);

        //then
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM entity_schema.player " +
                    "where name = ? and password = ?;");
            preparedStatement.setString(1, testName);
            preparedStatement.setString(2, testPassword);
            ResultSet resultSet = preparedStatement.executeQuery();

            assertTrue(resultSet.next());
            assertEquals(testName, resultSet.getString("name"));
            assertEquals(testPassword, resultSet.getString("password"));

            PreparedStatement preparedStatement1 = connection.prepareStatement("SELECT * FROM service_schema.player_history " +
                    "where id = ?");
            preparedStatement1.setInt(1, 1);
            ResultSet resultSet1 = preparedStatement1.executeQuery();

            assertTrue(resultSet1.next());
            assertEquals(PlayerHistory.REG.toString(), resultSet1.getString("what_player_doing"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void shouldProperlyLoginPlayer() {
        //given
        PlayerRepository playerRepository = new PlayerRepository(connection);
        String testName = "John";
        String testPassword = "123";
        playerRepository.registrationPlayer(testName, testPassword);

        //when
        playerRepository.loginPlayer(testName, testPassword);

        //then
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM entity_schema.player " +
                    "where name = ? and password = ?;");
            preparedStatement.setString(1, testName);
            preparedStatement.setString(2, testPassword);
            ResultSet resultSet = preparedStatement.executeQuery();

            assertTrue(resultSet.next());
            assertEquals(testName, resultSet.getString("name"));
            assertEquals(testPassword, resultSet.getString("password"));

            PreparedStatement preparedStatement1 = connection.prepareStatement("SELECT * FROM service_schema.player_history " +
                    "where id = ?");
            preparedStatement1.setInt(1, 2);
            ResultSet resultSet1 = preparedStatement1.executeQuery();

            assertTrue(resultSet1.next());
            assertEquals(PlayerHistory.LOG.toString(), resultSet1.getString("what_player_doing"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void shouldProperlyGetPlayer() {
        //given
        PlayerRepository playerRepository = new PlayerRepository(connection);
        String testName = "John";
        String testPassword = "123";
        playerRepository.registrationPlayer(testName, testPassword);

        //when
        Player player = playerRepository.getPlayer();

        //then
        assertEquals(3, player.getId());
        assertEquals(testName, player.getName());
        assertEquals(testPassword, player.getPassword());
        assertEquals(100, player.getBalance());
    }

    @Test
    void shouldProperlyUpdatePlayer() {
        //given
        PlayerRepository playerRepository = new PlayerRepository(connection);
        String testName = "Ivan";
        String testPassword = "123";
        playerRepository.registrationPlayer(testName, testPassword);

        //when
        playerRepository.updatePlayer(120);

        //then
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM entity_schema.player " +
                    "where name = ? and password = ?;");
            preparedStatement.setString(1, testName);
            preparedStatement.setString(2, testPassword);
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next())
                assertEquals(120, resultSet.getInt("balance"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}