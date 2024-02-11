package org.example.repository;

import org.example.repository.entity.Player;
import org.example.repository.entity.PlayerHistory;

import java.sql.*;

/**
 * Repository for {@link Player} interaction with the database
 */
public class PlayerRepository {
    private static int id;
    private final Connection connection;

    public PlayerRepository(Connection connection) {
        this.connection = connection;
    }

    public void registrationPlayer(String name, String password) {
        try {
            String sql = "insert into entity_schema.player(id,name,password) " +
                    "values(nextval('entity_schema.player_sequence'),?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, password);
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if(resultSet.next()) id = resultSet.getInt(1);
            addEnumToDatabase(id, PlayerHistory.REG);
            connection.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void loginPlayer(String name, String password) {
        try {
            String sql = "select * from entity_schema.player where name = ? and password = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) id = resultSet.getInt(1);
            addEnumToDatabase(id, PlayerHistory.LOG);

            connection.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void getPlayerHistory() {
        String sql = "select * from service_schema.player_history where player_id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                System.out.println(resultSet.getString(3) + ", ");
            }
            connection.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public Player getPlayer() {
        String sql = "select * from entity_schema.player where id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            Player player = new Player();
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                player.setId(resultSet.getInt(1));
                player.setName(resultSet.getString(2));
                player.setPassword(resultSet.getString(3));
                player.setBalance(resultSet.getInt(4));
            }
            return player;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void updatePlayer(int value) {
        String sql = "update entity_schema.player set balance = ? where id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, value);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void addEnumToDatabase(int id, PlayerHistory playerHistory) {
        try {
            String sql = "insert into service_schema.player_history(id, player_id, what_player_doing) " +
                    "values(nextval('service_schema.player_history_sequence'),?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, playerHistory.toString());
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}