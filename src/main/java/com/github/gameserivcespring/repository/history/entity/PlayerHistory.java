package com.github.gameserivcespring.repository.history.entity;

import com.github.gameserivcespring.repository.player.entity.Player;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Table(name = "player_history", schema = "service_schema")
@SequenceGenerator(
        name = "player_history_sequence",
        sequenceName = "service_schema.player_history_sequence",
        allocationSize = 1,
        schema = "service_schema"
)
public record PlayerHistory(
        @Id
        @Column(name = "id")
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "player_history_sequence")
        Integer id,

        @ManyToOne
        @JoinColumn(name = "player_id", referencedColumnName = "id")
        Player player,

        @Column(name = "what_player_doing")
        String whatPlayerDoing
) {
        public PlayerHistory() {
                this(null, null, null);
        }
}