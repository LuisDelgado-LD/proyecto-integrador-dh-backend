package com.dgitalhouse.integradorBackend.DTO.salida;

import com.dgitalhouse.integradorBackend.entity.Mascota;

public record MascotaSalidaDto(
    Long id,
    String nombre,
    String raza,
    String tamano,
    int edad
) {
    public MascotaSalidaDto(Mascota mascota) {
        this(mascota.getId(),
                mascota.getNombre(),
                mascota.getRaza(),
                mascota.getTamano(),
                mascota.getEdad());
    }

}
