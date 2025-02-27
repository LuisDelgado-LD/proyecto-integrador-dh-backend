package com.dgitalhouse.integradorBackend.controller;

import com.dgitalhouse.integradorBackend.DTO.userDTOS.LoginRequest;
import com.dgitalhouse.integradorBackend.DTO.userDTOS.TokenResponse;
import com.dgitalhouse.integradorBackend.DTO.userDTOS.UserRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api/auth")
public interface AuthApi {
    @PostMapping(value = "/register")
    ResponseEntity<TokenResponse> crearUsuario(
            @RequestBody @Valid UserRequest userRequest
            );
    @PostMapping(value = "/login")
    ResponseEntity <TokenResponse> login(
            @RequestBody @Valid LoginRequest loginRequest
            );
}
