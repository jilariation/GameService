package org.example.service;

import org.example.repository.TransactionRepository;
import org.example.repository.entity.Player;

public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepository transactionRepository = new TransactionRepository();
    @Override
    public int debitTransaction(int debit, int debitId) {
        return transactionRepository.debitTransaction(debit, debitId);
    }

    @Override
    public int creditTransaction(int credit, int creditId) {
        return transactionRepository.creditTransaction(credit, creditId);
    }

    @Override
    public void transactionHistory(Player player) {
        transactionRepository.transactionHistory(player);
    }
}
