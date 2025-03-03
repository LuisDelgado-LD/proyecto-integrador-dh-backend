package com.dgitalhouse.integradorBackend.service.Impl;

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
}
