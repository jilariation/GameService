package com.github.gameserivcespring.transactionservice.repository;

import com.github.gameserivcespring.transactionservice.repository.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

/**
 * Репозиторий для {@link Transaction}
 */
@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
    /**
     * Возвращает модель транзакции по ее ID
     * @param id ID транзакции
     * @return Возращает модель транзакции
     */
    Optional<Transaction> findById(UUID id);

    /**
     * Удаляет транзакцию по ее ID
     * @param id ID транзакции
     */
    void deleteById(UUID id);
}
