package com.dgitalhouse.integradorBackend.service.Impl;

import com.dgitalhouse.integradorBackend.entity.Usuario;
import com.dgitalhouse.integradorBackend.repository.UsuarioRepository;
import com.dgitalhouse.integradorBackend.service.UserCrudService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserCudServiceImpl implements UserCrudService {
    private final UsuarioRepository usuarioRepository;

    public UserCudServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }


    @Override
    public Usuario getUserCrud(Long Id) {
        return Optional.of(Id)
                .flatMap(usuarioRepository::findById)
                .orElseThrow(()-> new RuntimeException("No se pudo encontrar al usuario"));
    }

    @Override
    public List<Usuario> getAllUsers() {
        return usuarioRepository.findAll();
    }
}
