package com.example.demo.services;

import com.example.demo.payload.request.LoginRequest;
import com.example.demo.payload.request.SignupRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;


/**
 * AuthService
 * Arthur: kientt
 */
public interface AuthService {
    ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest);

    ResponseEntity<?> registerUser(@RequestBody SignupRequest signUpRequest);
}
