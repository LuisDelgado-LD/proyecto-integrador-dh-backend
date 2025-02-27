package com.dgitalhouse.integradorBackend.service;

import com.dgitalhouse.integradorBackend.DTO.userDTOS.TokenResponse;
import io.jsonwebtoken.Claims;

public interface JwtService {
    TokenResponse generateToken(Long id);
    Claims getClaims(String token);
    boolean isExpired(String token);
    Integer extractedUserId(String token);
}
