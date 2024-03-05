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
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Player implements UserDetails {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "player_seq_gen")
    @SequenceGenerator(name = "player_seq_gen", sequenceName = "entity_schema.player_sequence", allocationSize = 1,
            schema = "entity_schema")
    @NotNull
    private Integer id;

    @Column(name = "mail")
    @NotEmpty
    @Size(min = 2, max = 100, message = "Mail is incorrect")
    @Email
    private String mail;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    @NotEmpty
    @Size(min = 5, max = 100, message = "Password is incorrect")
    private String password;

    @Column(name = "balance")
    @Min(0)
    @NotNull
    private Integer balance = 100;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private Role role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
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
