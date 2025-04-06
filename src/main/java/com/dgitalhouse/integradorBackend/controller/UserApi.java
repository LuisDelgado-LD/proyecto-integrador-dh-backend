package com.dgitalhouse.integradorBackend.controller;

import com.dgitalhouse.integradorBackend.DTO.userDTOS.UpdateUserRequest;
import com.dgitalhouse.integradorBackend.DTO.userDTOS.UpdateUserRol;
import com.dgitalhouse.integradorBackend.DTO.userDTOS.UserInfoRequest;
import com.dgitalhouse.integradorBackend.entity.Usuario;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/auth")
//@SecurityRequirement(name= "Bearer Authentication")

//@CrossOrigin(origins = "*", allowedHeaders = "*")
public interface UserApi {
    @GetMapping()
    ResponseEntity<Usuario> getUser(
            @RequestAttribute("X-User-Id") Long Id
    );

    @DeleteMapping(value= "/deleteUser")
    ResponseEntity<Usuario>deleteUser(
            @RequestBody UserInfoRequest userInfoRequest
            );

    @PutMapping(value="/updateUser")
    ResponseEntity<Usuario>updateUser(
            @RequestAttribute("X-User-Id") Long Id,
            @RequestBody UpdateUserRequest updateUserRequest
            );

    @PutMapping(value="/updateRol/{Id}")
    ResponseEntity<Usuario>updateRol(
            @PathVariable Long Id,
            @RequestBody UpdateUserRol updateUserRol
            );

}
