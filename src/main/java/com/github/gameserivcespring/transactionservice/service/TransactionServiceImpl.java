package com.github.gameserivcespring.transactionservice.service;

import com.github.gameserivcespring.transactionservice.repository.TransactionRepository;
import com.github.gameserivcespring.transactionservice.repository.dto.TransactionDTO;
import com.github.gameserivcespring.transactionservice.repository.entity.Transaction;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepository transactionRepository;
    private final ModelMapper modelMapper;

    @Override
    @Transactional(readOnly = true)
    public Optional<Transaction> findById(int id) {
        return transactionRepository.findById(id);
    }

    @Override
    @Transactional
    public void save(Transaction transaction) {
        transactionRepository.save(transaction);
    }

    @Override
    public Transaction convertToTransaction(TransactionDTO transactionDTO) {
        return modelMapper.map(transactionDTO, Transaction.class);
    }
}
