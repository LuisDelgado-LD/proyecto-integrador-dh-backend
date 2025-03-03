package com.dgitalhouse.integradorBackend.DTO.salida;

import com.dgitalhouse.integradorBackend.entity.Categoria;
import com.dgitalhouse.integradorBackend.entity.Imagen;

import java.util.List;

public record CategoriaSalidaDto(
        Long id,
        String nombre,
        String descripcion,
        String imagenUrl
) {
    public CategoriaSalidaDto(Categoria categoria) {
        this(categoria.getId(), categoria.getNombre(), categoria.getDescripcion(), categoria.getImagenUrl());
    }
}
