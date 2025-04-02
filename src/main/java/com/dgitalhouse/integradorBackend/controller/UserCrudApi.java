package com.dgitalhouse.integradorBackend.controller;

import com.dgitalhouse.integradorBackend.entity.Usuario;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/api/user")
public interface UserCrudApi {
    @GetMapping(value="/{Id}")
    ResponseEntity<Usuario> getUserCrud(
            @PathVariable Long Id
    );

    @GetMapping
    ResponseEntity<List<Usuario>> getAllUsers();


}
