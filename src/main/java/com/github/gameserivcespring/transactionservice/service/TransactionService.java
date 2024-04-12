package com.github.gameserivcespring.transactionservice.service;

import com.github.gameserivcespring.transactionservice.repository.dto.TransactionDTO;
import com.github.gameserivcespring.transactionservice.repository.entity.Transaction;

import java.util.Optional;

/**
 * Сервис для {@link Transaction}
 */
public interface TransactionService {
    /**
     * Находит транзакцию по ее ID
     * @param id ID транзакции
     * @return Возвращает либо транзацию, либо null
     */
    Optional<Transaction> findById(int id);

    /**
     * Сохраняет транзакцию в базу данных
     * @param transaction Сама транзакция
     */
    void save(Transaction transaction);

    /**
     * Преобразует {@link TransactionDTO} в {@link Transaction}
     * @param transactionDTO Входящее DTO транзакции
     * @return Возращает {@link Transaction}
     */
    Transaction convertToTransaction(TransactionDTO transactionDTO);
}
