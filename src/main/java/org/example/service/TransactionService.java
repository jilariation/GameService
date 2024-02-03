package org.example.service;

import org.example.repository.entity.Player;

public interface TransactionService {
    int debitTransaction(int debit, int debitId);
    int creditTransaction(int credit, int creditId);
    void transactionHistory(Player player);
}
