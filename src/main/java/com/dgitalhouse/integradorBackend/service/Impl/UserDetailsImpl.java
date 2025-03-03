package com.dgitalhouse.integradorBackend.service.Impl;
import com.dgitalhouse.integradorBackend.repository.UsuarioRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service

public class UserDetailsImpl implements  UserDetailsService{
    private final UsuarioRepository usuarioRepository;

    public UserDetailsImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return usuarioRepository.findByEmail(username)
                .orElseThrow(()-> new RuntimeException("Usuario no encontrado"));
    }
}
