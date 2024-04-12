package com.github.gameserivcespring.transactionservice.repository.entity;

import com.github.gameserivcespring.userservice.repository.entity.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "transaction",schema = "entity_schema")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {
    @Id
    @Column(name = "id")
    @NotNull
    private Integer id;

    @Column(name = "value")
    @NotNull
    @Min(1)
    private Integer value;

    @Column(name = "transaction_type")
    @NotNull
    private String transactionType;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "player_id", referencedColumnName = "id")
    private User user;
}