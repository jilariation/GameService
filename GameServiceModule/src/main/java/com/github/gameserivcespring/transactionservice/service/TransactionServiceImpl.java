package com.github.gameserivcespring.transactionservice.service;

import com.github.gameserivcespring.transactionservice.repository.TransactionRepository;
import com.github.gameserivcespring.transactionservice.repository.dto.TransactionDTO;
import com.github.gameserivcespring.transactionservice.repository.entity.Transaction;
import com.github.gameserivcespring.userservice.automation.AbstractService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl extends AbstractService<Transaction, TransactionDTO, UUID> implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final ModelMapper modelMapper;

    @Override
    @Transactional
    public Transaction save(Transaction model) {
        return transactionRepository.save(model);
    }

    @Override
    @Transactional
    public Optional<Transaction> getModel(UUID id) {
        return transactionRepository.findById(id);
    }

    @Override
    @Transactional
    public Transaction updateModel(Transaction updateModel) {
        return transactionRepository.save(updateModel);
    }

    @Override
    @Transactional
    public void deleteById(UUID id) {
        transactionRepository.deleteById(id);
    }

    @Override
    public Transaction convertToModel(TransactionDTO dto) {
        return modelMapper.map(dto, Transaction.class);
    }

    @Override
    public TransactionDTO convertToDTO(Transaction model) {
        return modelMapper.map(model, TransactionDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Transaction> findById(UUID id) {
        return transactionRepository.findById(id);
    }
}
