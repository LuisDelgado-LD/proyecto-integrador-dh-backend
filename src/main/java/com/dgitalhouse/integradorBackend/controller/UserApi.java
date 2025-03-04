package com.dgitalhouse.integradorBackend.controller;

import com.dgitalhouse.integradorBackend.entity.Usuario;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api/auth")
@SecurityRequirement(name= "Bearer Authentication")

@CrossOrigin(origins = "*", allowedHeaders = "*")
public interface UserApi {
    @GetMapping()
    ResponseEntity<Usuario> getUser(
            @RequestAttribute("X-User-Id") Long Id
    );
}
