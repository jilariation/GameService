package org.example.service;

import org.example.repository.TransactionRepository;
import org.example.connection.DatabaseConnection;
import org.example.repository.entity.Player;
import org.example.repository.entity.Transaction;

import java.util.List;

/**
 * Implementation of {@link TransactionRepository} interface
 */
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepository transactionRepository = new TransactionRepository(
            new DatabaseConnection().getConnection());
    @Override
    public int debitTransaction(int debit, int debitId) {
        return transactionRepository.debitTransaction(debit, debitId);
    }

    @Override
    public int creditTransaction(int credit, int creditId) {
        return transactionRepository.creditTransaction(credit, creditId);
    }

    @Override
    public List<Transaction> transactionHistory(Player player) {
        return transactionRepository.transactionHistory(player);
    }
}
