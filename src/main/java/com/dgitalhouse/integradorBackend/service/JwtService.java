package com.dgitalhouse.integradorBackend.service;

import com.dgitalhouse.integradorBackend.DTO.userDTOS.TokenResponse;

public interface JwtService {
    TokenResponse generateToken(Long userId);
    Claims getClaims(String token);
    boolean isExpired(String token);
    Integer extractedUserId(String token);
}
