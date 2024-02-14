package com.github.gameserivcespring.controller;

import com.github.gameserivcespring.repository.dto.TransactionDTO;
import com.github.gameserivcespring.repository.entity.PlayerHistory;
import com.github.gameserivcespring.repository.entity.PlayerHistoryEnum;
import com.github.gameserivcespring.repository.entity.Transaction;
import com.github.gameserivcespring.service.player.PlayerHistoryService;
import com.github.gameserivcespring.service.player.PlayerService;
import com.github.gameserivcespring.service.transaction.TransactionService;
import com.github.gameserivcespring.validator.transaction.TransactionCreditValidator;
import com.github.gameserivcespring.validator.transaction.TransactionDebitValidator;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/player")
public class TransactionController {
    private final PlayerService playerService;
    private final TransactionService transactionService;
    private final PlayerHistoryService playerHistoryService;
    private final TransactionDebitValidator transactionDebitValidator;
    private final TransactionCreditValidator transactionCreditValidator;
    private final ModelMapper modelMapper;

    @Autowired
    public TransactionController(PlayerService playerService, TransactionService transactionService,
                                 PlayerHistoryService playerHistoryService, TransactionDebitValidator transactionDebitValidator,
                                 TransactionCreditValidator transactionCreditValidator, ModelMapper modelMapper) {
        this.playerService = playerService;
        this.transactionService = transactionService;
        this.playerHistoryService = playerHistoryService;
        this.transactionDebitValidator = transactionDebitValidator;
        this.transactionCreditValidator = transactionCreditValidator;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/{id}/transaction/debit")
    public ResponseEntity<HttpStatus> debit(@RequestBody @Valid TransactionDTO transactionDTO,
                                            @PathVariable int id,
                                            BindingResult bindingResult) {
        Transaction transaction = convertToTransaction(transactionDTO);
        transaction.setPlayer(playerService.findById(id).get());
        transactionDebitValidator.validate(transaction, bindingResult);
        transactionService.save(transaction);
        transaction.getPlayer().setBalance(transaction.getPlayer().getBalance() - transaction.getValue());
        playerService.update(transaction.getPlayer(), id);
        playerHistoryService.save(new PlayerHistory(transaction.getPlayer(), PlayerHistoryEnum.DEBIT.name()));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PostMapping("/{id}/transaction/credit")
    public ResponseEntity<HttpStatus> credit(@RequestBody @Valid TransactionDTO transactionDTO,
                                            @PathVariable int id,
                                            BindingResult bindingResult) {
        Transaction transaction = convertToTransaction(transactionDTO);
        transaction.setPlayer(playerService.findById(id).get());
        transactionCreditValidator.validate(transaction, bindingResult);
        transactionService.save(transaction);
        transaction.getPlayer().setBalance(transaction.getPlayer().getBalance() + transaction.getValue());
        playerService.update(transaction.getPlayer(), id);
        playerHistoryService.save(new PlayerHistory(transaction.getPlayer(), PlayerHistoryEnum.CREDIT.name()));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    private Transaction convertToTransaction(TransactionDTO transactionDTO) {
        return modelMapper.map(transactionDTO, Transaction.class);
    }
    private TransactionDTO convertToTransactionDTO(Transaction transaction) {
        return modelMapper.map(transaction, TransactionDTO.class);
    }
}
