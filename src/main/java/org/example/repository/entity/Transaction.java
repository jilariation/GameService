package org.example.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Transaction {
    private Integer id;
    private Integer value;
    private TransactionType transactionType;
}
