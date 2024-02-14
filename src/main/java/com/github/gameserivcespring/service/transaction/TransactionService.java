package com.github.gameserivcespring.service.transaction;

import com.github.gameserivcespring.repository.entity.Transaction;

import java.util.Optional;

public interface TransactionService {
    Optional<Transaction> findById(int id);
    void save(Transaction transaction);
}
