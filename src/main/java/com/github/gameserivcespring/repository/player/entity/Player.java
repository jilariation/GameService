package com.github.gameserivcespring.repository.player.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "player", schema = "entity_schema")
@Builder
public record Player(
        @Id
        @Column(name = "id")
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "player_seq_gen")
        @SequenceGenerator(name = "player_seq_gen", sequenceName = "entity_schema.player_sequence", allocationSize = 1,
                schema = "entity_schema")
        Integer id,

        @Column(name = "mail")
        String mail,

        @Column(name = "username")
        String username,

        @Column(name = "password")
        String password,

        @Column(name = "balance")
        Integer balance,

        @Enumerated(EnumType.STRING)
        @Column(name = "role", nullable = false)
        Role role
) implements UserDetails {

    public Player() {
        this(null, null, null, null, null, null);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
