package org.example.repository;

import org.example.connection.DatabaseConnection;
import org.example.repository.entity.Player;
import org.example.repository.entity.PlayerHistory;
import org.example.repository.entity.Transaction;
import org.example.repository.entity.TransactionType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Repository for {@link Transaction} interaction with the database
 */
public class TransactionRepository {
    private final PlayerRepository playerRepository = new PlayerRepository(new DatabaseConnection().getConnection());
    private final Connection connection;

    public TransactionRepository(Connection connection) {
        this.connection = connection;
    }


    public int debitTransaction(int debit, int debitId) {
        Player player = playerRepository.getPlayer();
        if(checkTransactionList(debitId)) {
            return 1;
        }
        if(player.getBalance() - debit < 0) {
            return 2;
        }
        String sql = "insert into entity_schema.transaction(id, value, transaction_type, player_id) values(?,?,?,?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, debitId);
            preparedStatement.setInt(2, debit);
            preparedStatement.setString(3, TransactionType.DEBIT.toString());
            preparedStatement.setInt(4, player.getId());
            preparedStatement.executeUpdate();
            playerRepository.updatePlayer(player.getBalance() - debit);
            playerRepository.addEnumToDatabase(playerRepository.getPlayer().getId(), PlayerHistory.DEBIT);
            connection.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }

    public int creditTransaction(int credit, int creditId) {
        Player player = playerRepository.getPlayer();
        if(checkTransactionList(creditId)) {
            System.out.println("Индетификатор неуникален, повторите дебит заново");
            return 1;
        }
        String sql = "insert into entity_schema.transaction(id, value, transaction_type, player_id) values(?,?,?,?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, creditId);
            preparedStatement.setInt(2, credit);
            preparedStatement.setString(3, TransactionType.CREDIT.toString());
            preparedStatement.setInt(4, player.getId());
            preparedStatement.executeUpdate();
            playerRepository.updatePlayer(player.getBalance() + credit);
            playerRepository.addEnumToDatabase(playerRepository.getPlayer().getId(), PlayerHistory.CREDIT);
            connection.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }

    public List<Transaction> transactionHistory(Player player) {
        String sql = "select * from entity_schema.transaction where player_id = ?";
        List<Transaction> transactionList = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, player.getId());
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                transactionList.add(new Transaction(
                        resultSet.getInt("id"),
                        resultSet.getInt("value"),
                        TransactionType.valueOf(resultSet.getString("transaction_type"))
                ));
            }
            playerRepository.addEnumToDatabase(playerRepository.getPlayer().getId(), PlayerHistory.TRANSACTION_HISTORY);
            connection.commit();
            return transactionList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private boolean checkTransactionList(int debitId) {
        String sql = "select * from entity_schema.transaction";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                if(resultSet.getInt(1) == debitId) {
                    return true;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }
}
