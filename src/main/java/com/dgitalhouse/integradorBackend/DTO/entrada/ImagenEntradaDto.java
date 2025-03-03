package com.dgitalhouse.integradorBackend.DTO.entrada;

import com.dgitalhouse.integradorBackend.entity.Habitacion;
import com.dgitalhouse.integradorBackend.entity.Imagen;

public record ImagenEntradaDto(
    Long id,
    String url

) {
    public ImagenEntradaDto(Imagen imagenActualizada) {
        this(imagenActualizada.getId(), imagenActualizada.getUrl());
    }
}
