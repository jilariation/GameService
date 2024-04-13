package com.github.gameserivcespring.transactionservice.service;

import com.github.gameserivcespring.transactionservice.repository.dto.TransactionDTO;
import com.github.gameserivcespring.transactionservice.repository.entity.Transaction;

import java.util.Optional;
import java.util.UUID;

/**
 * Сервис для {@link Transaction}
 */
public interface TransactionService {
    /**
     * Находит транзакцию по ее ID
     * @param id ID транзакции
     * @return Возвращает либо транзацию, либо null
     */
    Optional<Transaction> findById(UUID id);
}
