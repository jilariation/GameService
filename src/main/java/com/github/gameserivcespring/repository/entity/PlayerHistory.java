package com.github.gameserivcespring.repository.entity;

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
@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
public class PlayerHistory {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "player_history_sequence")
    @NotNull
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "player_id", referencedColumnName = "id")
    @NotNull
    @NonNull private Player player;

    @Column(name = "what_player_doing")
    @NotNull
    @NonNull private String whatPlayerDoing;
}
