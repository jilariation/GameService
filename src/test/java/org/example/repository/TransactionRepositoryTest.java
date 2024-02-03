package org.example.repository;

import org.example.repository.entity.Player;
import org.example.repository.entity.PlayerHistory;
import org.example.repository.entity.Transaction;
import org.example.repository.entity.TransactionType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class TransactionRepositoryTest {
    private TransactionRepository transactionRepository;

    @BeforeEach
    void init() {
        transactionRepository = new TransactionRepository();
    }

    @Test
    void shouldProperlyDebitTransaction() {
        //given
        Player player = new Player(1, "John", 100, new ArrayList<>(), new ArrayList<>());
        PlayerRepository.setPlayer(player);
        PlayerRepository.getPlayers().add(player);
        Transaction transaction = new Transaction(1, 10, TransactionType.DEBIT);

        //when
        int returnValue = transactionRepository.debitTransaction(10, 1);
        int returnValue1 = transactionRepository.debitTransaction(10, 1);
        int returnValue2 = transactionRepository.debitTransaction(120, 2);

        //then
        assertEquals(0, returnValue);
        assertEquals(1, returnValue1);
        assertEquals(2, returnValue2);
        assertEquals(90, player.getBalance());
        assertEquals(player.getTransactionList().get(0).getId(), transaction.getId());
        assertEquals(player.getTransactionList().get(0).getValue(), transaction.getValue());
        assertEquals(player.getTransactionList().get(0).getTransactionType(), transaction.getTransactionType());
        assertEquals(player.getPlayerHistory().get(0), PlayerHistory.DEBIT);
    }

    @Test
    void shouldProperlyCreditTransaction() {
        //given
        Player player = new Player(1, "John", 100, new ArrayList<>(), new ArrayList<>());
        PlayerRepository.setPlayer(player);
        PlayerRepository.getPlayers().add(player);
        Transaction transaction = new Transaction(1, 10, TransactionType.CREDIT);

        //when
        int returnValue = transactionRepository.creditTransaction(10,1);
        int returnValue1 = transactionRepository.creditTransaction(10, 1);

        //then
        assertEquals(0, returnValue);
        assertEquals(1, returnValue1);
        assertEquals(110, player.getBalance());
        assertEquals(player.getTransactionList().get(0).getId(), transaction.getId());
        assertEquals(player.getTransactionList().get(0).getValue(), transaction.getValue());
        assertEquals(player.getTransactionList().get(0).getTransactionType(), transaction.getTransactionType());
        assertEquals(player.getPlayerHistory().get(0), PlayerHistory.CREDIT);
    }
}
