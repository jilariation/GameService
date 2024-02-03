package org.example.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Player {
    private Integer id;
    private String name;
    private Integer balance;
    private List<Transaction> transactionList;
    private List<PlayerHistory> playerHistory;
}
