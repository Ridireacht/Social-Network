package com.socialnetwork.authservice.service.impl;

import com.socialnetwork.authservice.dto.request.SignInRequest;
import com.socialnetwork.authservice.dto.request.SignUpRequest;
import com.socialnetwork.authservice.dto.response.JwtAuthenticationResponse;
import com.socialnetwork.authservice.entity.Role;
import com.socialnetwork.authservice.entity.User;
import com.socialnetwork.authservice.repository.UserRepository;
import com.socialnetwork.authservice.service.AuthenticationService;
import com.socialnetwork.authservice.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public JwtAuthenticationResponse signup(SignUpRequest request) {
        var user = new User();

        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(Role.USER);

        userRepository.save(user);

        var jwt = jwtService.generateToken(user);

        var authenticationResponse = new JwtAuthenticationResponse();
        authenticationResponse.setToken(jwt);

        return authenticationResponse;
    }

    @Override
    public JwtAuthenticationResponse signin(SignInRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getEmail(),
                request.getPassword())
        );

        var user = userRepository
                .findByEmail(request.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password"));

        var jwt = jwtService.generateToken(user);

        var authenticationResponse = new JwtAuthenticationResponse();
        authenticationResponse.setToken(jwt);

        return authenticationResponse;
    }
}
