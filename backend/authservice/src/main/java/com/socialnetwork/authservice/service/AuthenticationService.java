package com.socialnetwork.authservice.service;

import com.socialnetwork.authservice.dto.request.SignInRequest;
import com.socialnetwork.authservice.dto.request.SignUpRequest;
import com.socialnetwork.authservice.dto.response.JwtAuthenticationResponse;

public interface AuthenticationService {
    JwtAuthenticationResponse signup(SignUpRequest request);

    JwtAuthenticationResponse signin(SignInRequest request);
}
