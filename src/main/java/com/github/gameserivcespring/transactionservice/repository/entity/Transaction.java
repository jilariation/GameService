package com.github.gameserivcespring.transactionservice.repository.entity;

import com.github.gameserivcespring.userservice.automation.AbstractModel;
import com.github.gameserivcespring.userservice.repository.entity.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "transaction",schema = "entity_schema")
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class Transaction extends AbstractModel {

    @Column(name = "value")
    private Integer value;

    @Column(name = "transaction_type")
    private String transactionType;

    @ManyToOne
    @JoinColumn(name = "player_id", referencedColumnName = "id")
    private User user;
}
