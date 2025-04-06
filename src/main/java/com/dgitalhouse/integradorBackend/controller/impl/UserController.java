package com.dgitalhouse.integradorBackend.controller.impl;

import com.dgitalhouse.integradorBackend.DTO.userDTOS.UpdateUserRequest;
import com.dgitalhouse.integradorBackend.DTO.userDTOS.UpdateUserRol;
import com.dgitalhouse.integradorBackend.DTO.userDTOS.UserInfoRequest;
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

    @Override
    public ResponseEntity<Usuario> deleteUser(UserInfoRequest userInfoRequest) {
        userService.deleteUser(userInfoRequest);

        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<Usuario> updateUser(Long Id, UpdateUserRequest updateUserRequest) {
        userService.updateUser(Id,updateUserRequest);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<Usuario> updateRol(Long Id, UpdateUserRol updateUserRol) {
        userService.updateRol(Id,updateUserRol);
        return null;
    }
}
