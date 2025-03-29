package com.dgitalhouse.integradorBackend.controller;

import com.dgitalhouse.integradorBackend.service.IUsuarioService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private IUsuarioService usuarioService;

}
