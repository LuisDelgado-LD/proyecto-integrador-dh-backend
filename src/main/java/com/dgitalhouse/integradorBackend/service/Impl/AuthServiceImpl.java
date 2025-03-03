package com.dgitalhouse.integradorBackend.service.Impl;

import com.dgitalhouse.integradorBackend.DTO.userDTOS.LoginRequest;
import com.dgitalhouse.integradorBackend.DTO.userDTOS.TokenResponse;
import com.dgitalhouse.integradorBackend.DTO.userDTOS.UserRequest;
import com.dgitalhouse.integradorBackend.entity.Usuario;
import com.dgitalhouse.integradorBackend.repository.UsuarioRepository;
import com.dgitalhouse.integradorBackend.service.AuthService;
import com.dgitalhouse.integradorBackend.service.JwtService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class AuthServiceImpl implements AuthService {
    private final UsuarioRepository usuarioRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    public AuthServiceImpl(UsuarioRepository usuarioRepository, JwtService jwtService, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public TokenResponse crearUsuario(UserRequest userRequest) {
        return Optional.of(userRequest)
                .map(this::mapToEntity)
                .map(usuarioRepository::save)
                .map(userCreated -> jwtService.generateToken(userCreated.getId()))
                .orElseThrow(() -> new RuntimeException("Error al crear al usuario"));
    }

    private Usuario mapToEntity(UserRequest userRequest) {
        return Usuario.builder()
                .email(userRequest.getEmail())
                .password(passwordEncoder.encode(userRequest.getPassword()))
                .nombre(userRequest.getNombre())
                .apellido(userRequest.getApellido())
                .direccion(userRequest.getDireccion())
                .telefono(userRequest.getTelefono())
                .rol("USUARIO")
                .build();

    }

    @Override
    public TokenResponse login(LoginRequest loginRequest) {
        return Optional.of(loginRequest.getEmail())
                .map(usuarioRepository :: findByEmail)
                .filter(user -> passwordEncoder.matches(loginRequest.getPassword(), user.get().getPassword()  ))
                .map(user -> jwtService.generateToken(user.get().getId()))
                .orElseThrow(() -> new RuntimeException("Fallo al  procesar el login"));
    }
}
