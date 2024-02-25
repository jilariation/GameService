package com.github.gameserivcespring.service.transaction;

import com.github.gameserivcespring.repository.entity.Transaction;

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
}
