package com.github.gameserivcespring.userservice.service;

import com.github.gameserivcespring.userservice.repository.dto.SignInRequest;
import com.github.gameserivcespring.userservice.repository.dto.SignUpRequest;
import com.github.gameserivcespring.userservice.repository.dto.JwtAuthenticationResponse;
import com.github.gameserivcespring.userservice.repository.entity.Role;
import com.github.gameserivcespring.userservice.repository.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final PlayerService playerService;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    @Override
    @Transactional
    public JwtAuthenticationResponse signUp(SignUpRequest request) {
        var user = new User(
                request.getUsername(),
                request.getMail(),
                passwordEncoder.encode(request.getPassword()),
                100,
                Role.ROLE_USER
        );

        var jwt = jwtService.generateToken(user);
        return new JwtAuthenticationResponse(jwt);
    }

    @Override
    public JwtAuthenticationResponse signIn(SignInRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getUsername(),
                request.getPassword()
        ));

        var player = playerService
                .userDetailsService()
                .loadUserByUsername(request.getUsername());

        var jwt = jwtService.generateToken(player);
        return new JwtAuthenticationResponse(jwt);
    }
}
