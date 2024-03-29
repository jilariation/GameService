package com.github.gameserivcespring.repository.transaction.entity;

import com.github.gameserivcespring.repository.player.entity.Player;
import jakarta.persistence.*;

@Entity
@Table(name = "transaction",schema = "entity_schema")
public record Transaction(
        @Id
        @Column(name = "id")
        Integer id,

        @Column(name = "value")
        Integer value,

        @Column(name = "transaction_type")
        String transactionType,

        @ManyToOne
        @JoinColumn(name = "player_id", referencedColumnName = "id")
        Player player
) {
        public Transaction() {
                this(null, null, null, null);
        }
}