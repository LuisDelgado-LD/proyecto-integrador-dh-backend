package com.dgitalhouse.integradorBackend.controller.impl;

import com.dgitalhouse.integradorBackend.controller.UserApi;
import com.dgitalhouse.integradorBackend.entity.Usuario;
import com.dgitalhouse.integradorBackend.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class UserController implements UserApi {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Override
    public ResponseEntity<Usuario> getUser(Long Id) {
        return ResponseEntity.ok(userService.getUser(Id));
    }
}
