package com.dgitalhouse.integradorBackend.controller.impl;

import com.dgitalhouse.integradorBackend.controller.UserCrudApi;
import com.dgitalhouse.integradorBackend.entity.Usuario;
import com.dgitalhouse.integradorBackend.service.UserCrudService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserCrudController implements UserCrudApi {
    private final UserCrudService userCrudService;

    public UserCrudController(UserCrudService userCrudService) {
        this.userCrudService = userCrudService;
    }


    @Override
    public ResponseEntity<Usuario> getUserCrud(Long Id) {
        return ResponseEntity.ok(userCrudService.getUserCrud(Id));
    }

    @Override
    public ResponseEntity<List<Usuario>> getAllUsers() {
        List<Usuario> usuarios = userCrudService.getAllUsers();
        return usuarios.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(usuarios);
    }


}
