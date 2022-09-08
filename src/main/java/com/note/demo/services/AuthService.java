package com.note.demo.services;

import com.note.demo.payload.request.LoginRequest;
import com.note.demo.payload.request.SignupRequest;
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
