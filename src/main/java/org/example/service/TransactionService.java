package org.example.service;

import org.example.repository.entity.Player;

/**
 * Service for processing transactions
 */
public interface TransactionService {
    /**
     * Performs a debit transaction
     * @param debit Debit value
     * @param debitId ID of debit transaction
     * @return Returns a number to process the function
     */
    int debitTransaction(int debit, int debitId);

    /**
     * Performs a credit transaction
     * @param credit Credit value
     * @param creditId ID of credit transaction
     * @return Returns a number to process the function
     */
    int creditTransaction(int credit, int creditId);

    /**
     * Displays the {@link Player}'s transaction history
     * @param player The player for whom you want to display his history
     */
    void transactionHistory(Player player);
}
