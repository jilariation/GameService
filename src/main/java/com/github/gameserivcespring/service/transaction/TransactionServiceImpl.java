package com.github.gameserivcespring.service.transaction;

import com.github.gameserivcespring.repository.transaction.TransactionRepository;
import com.github.gameserivcespring.repository.transaction.dto.TransactionDTO;
import com.github.gameserivcespring.repository.transaction.entity.Transaction;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepository transactionRepository;
    private final ModelMapper modelMapper;

    @Override
    public Optional<Transaction> findById(int id) {
        return transactionRepository.findById(id);
    }

    @Override
    public void save(Transaction transaction) {
        transactionRepository.save(transaction);
    }

    @Override
    public Transaction convertToTransaction(TransactionDTO transactionDTO) {
        return modelMapper.map(transactionDTO, Transaction.class);
    }
}
