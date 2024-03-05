package com.github.gameserivcespring.repository.transaction;

import com.github.gameserivcespring.repository.transaction.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Репозиторий для {@link Transaction}
 */
@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
}
