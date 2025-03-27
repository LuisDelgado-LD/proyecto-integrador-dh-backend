package com.dgitalhouse.integradorBackend.service.Impl;

import com.dgitalhouse.integradorBackend.DTO.userDTOS.UpdateUserRequest;
import com.dgitalhouse.integradorBackend.DTO.userDTOS.UserInfoRequest;
import com.dgitalhouse.integradorBackend.entity.Usuario;
import com.dgitalhouse.integradorBackend.repository.UsuarioRepository;
import com.dgitalhouse.integradorBackend.service.UserService;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class UserServiceImpl implements UserService {
    private final UsuarioRepository usuarioRepository;

    public UserServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public Usuario getUser(Long id) {
        return Optional.of(id)
                .flatMap(usuarioRepository :: findById)
                .orElseThrow(() -> new RuntimeException("Error, no se pudo encontrar al usuario"));
    }

    @Override
    public UserInfoRequest deleteUser(UserInfoRequest userInfoRequest) {
        Optional.of(userInfoRequest.getId())
                .map(this::getUser)
                .ifPresent(usuarioRepository::delete);
        return null;
    }

    @Override
    public UpdateUserRequest updateUser(Long Id, UpdateUserRequest updateUserRequest) {
        Optional.of(Id)
                .map(this::getUser)
                .map(existingUser -> updateUserFields(existingUser, updateUserRequest))
                .map(usuarioRepository :: save)
                .orElseThrow(() -> new RuntimeException("No se pudo actualizar al usuario."));
        return null;
    }

    private Usuario updateUserFields(Usuario existingUser, UpdateUserRequest updateUserRequest) {
        existingUser.setEmail(updateUserRequest.getEmail());
        existingUser.setNombre(updateUserRequest.getNombre());
        existingUser.setRol(updateUserRequest.getRol());
        return existingUser;
    }

}
