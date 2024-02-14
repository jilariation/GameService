package com.github.gameserivcespring.repository.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Table(name = "player", schema = "entity_schema")
@SequenceGenerator(
        name = "player_seq_gen",
        sequenceName = "entity_schema.player_sequence",
        allocationSize = 1,
        schema = "entity_schema")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Player {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "player_seq_gen")
    @NotNull
    private Integer id;

    @Column(name = "name")
    @NotEmpty
    @Size(min = 2, max = 100, message = "Name is incorrect")
    private String name;

    @Column(name = "password")
    @NotEmpty
    @Size(min = 5, max = 100, message = "Password is incorrect")
    private String password;

    @Column(name = "balance")
    @Min(0)
    @NotNull
    private Integer balance = 100;
}
