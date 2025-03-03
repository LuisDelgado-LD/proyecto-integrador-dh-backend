package com.dgitalhouse.integradorBackend.controller.impl;

import com.dgitalhouse.integradorBackend.DTO.userDTOS.LoginRequest;
import com.dgitalhouse.integradorBackend.DTO.userDTOS.TokenResponse;
import com.dgitalhouse.integradorBackend.DTO.userDTOS.UserRequest;
import com.dgitalhouse.integradorBackend.controller.AuthApi;
import com.dgitalhouse.integradorBackend.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class AuthController implements AuthApi {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @Override
    public ResponseEntity<TokenResponse> crearUsuario(UserRequest userRequest) {
        return ResponseEntity.ok(authService.crearUsuario(userRequest));
    }

    @Override
    public ResponseEntity<TokenResponse> login(LoginRequest loginRequest) {
        return ResponseEntity.ok(authService.login(loginRequest));
    }
}
