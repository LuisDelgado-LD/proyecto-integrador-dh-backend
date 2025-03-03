package com.dgitalhouse.integradorBackend.service;

import com.dgitalhouse.integradorBackend.DTO.entrada.CategoriaEntradaDto;
import com.dgitalhouse.integradorBackend.DTO.salida.CategoriaSalidaDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

public interface ICategoriaService {
    ResponseEntity<CategoriaSalidaDto> registrarCategoria(CategoriaEntradaDto categoriaEntradaDto, UriComponentsBuilder uriComponentsBuilder);

    ResponseEntity<CategoriaSalidaDto> obtenerCategoria(Long id);

    List<CategoriaSalidaDto> obtenerTodasCategorias();

    ResponseEntity<CategoriaSalidaDto> actualizarCategoria(Long id, CategoriaEntradaDto categoriaEntradaDto);

    boolean eliminarCategoria(Long id);
}
