package com.github.gameserivcespring.transactionservice.repository;

import com.github.gameserivcespring.transactionservice.repository.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Репозиторий для {@link Transaction}
 */
@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
}
