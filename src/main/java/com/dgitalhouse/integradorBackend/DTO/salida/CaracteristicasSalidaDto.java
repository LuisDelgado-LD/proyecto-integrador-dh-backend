package com.dgitalhouse.integradorBackend.DTO.salida;

import com.dgitalhouse.integradorBackend.entity.Caracteristicas;

public record CaracteristicasSalidaDto(
    Long id,
    String nombre,
    String icono
) {

    public CaracteristicasSalidaDto(Caracteristicas caracteristicas) {
        this(caracteristicas.getId(), caracteristicas.getNombre(), caracteristicas.getIconoUrl());
    }
}
