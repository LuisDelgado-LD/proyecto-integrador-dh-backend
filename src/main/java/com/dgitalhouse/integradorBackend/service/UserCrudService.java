package com.dgitalhouse.integradorBackend.service;

import com.dgitalhouse.integradorBackend.entity.Usuario;

import java.util.List;

public interface UserCrudService {
    Usuario getUserCrud(Long Id);
    List<Usuario> getAllUsers();
}
