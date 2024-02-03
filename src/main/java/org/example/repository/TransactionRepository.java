package org.example.repository;

import org.example.repository.entity.Player;
import org.example.repository.entity.PlayerHistory;
import org.example.repository.entity.Transaction;
import org.example.repository.entity.TransactionType;

public class TransactionRepository {
    private final PlayerRepository playerRepository = new PlayerRepository();

    public int debitTransaction(int debit, int debitId) {
        Player player = PlayerRepository.getPlayer();
        for(Transaction transaction : player.getTransactionList())
            if(transaction.getId() == debitId) {
                System.out.println("Индетификатор неуникален, повторите дебит заново");
                return 1;
            }
        if(player.getBalance() - debit < 0) {
            System.out.println("Дебит превышает ваше значение баланса. Пополните баланс или повторите дебит " +
                    "с меньшим значением");
            return 2;
        }
        player.getTransactionList().add(new Transaction(
                debitId,
                debit,
                TransactionType.DEBIT
        ));
        player.setBalance(player.getBalance() - debit);
        player.getPlayerHistory().add(PlayerHistory.DEBIT);
        return 0;
    }

    public int creditTransaction(int credit, int creditId) {
        Player player = PlayerRepository.getPlayer();
        for(Transaction transaction : player.getTransactionList())
            if(transaction.getId() == creditId) {
                System.out.println("Индетификатор неуникален, повторите кредит заново");
                return 1;
            }
        player.getTransactionList().add(new Transaction(
                creditId,
                credit,
                TransactionType.CREDIT
        ));
        player.setBalance(player.getBalance() + credit);
        player.getPlayerHistory().add(PlayerHistory.CREDIT);
        return 0;
    }

    public void transactionHistory(Player player) {
        for(Transaction transaction : player.getTransactionList())
            System.out.println(
                    "Id: " + transaction.getId() + "\n" +
                            "Value: " + transaction.getValue() + "\n" +
                            "TransactionType: " + transaction.getTransactionType() + "\n"
            );
        player.getPlayerHistory().add(PlayerHistory.TRANSACTION_HISTORY);
    }
}
