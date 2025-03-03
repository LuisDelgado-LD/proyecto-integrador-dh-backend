package com.dgitalhouse.integradorBackend.service;

import com.dgitalhouse.integradorBackend.DTO.userDTOS.LoginRequest;
import com.dgitalhouse.integradorBackend.DTO.userDTOS.TokenResponse;
import com.dgitalhouse.integradorBackend.DTO.userDTOS.UserRequest;

public interface AuthService {
    TokenResponse crearUsuario(UserRequest userRequest);
    TokenResponse login(LoginRequest loginRequest);
}
