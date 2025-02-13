package com.dgitalhouse.integradorBackend.DTO.salida;

public record MascotaSalidaDto(
    Long id,
    String nombre,
    String raza,
    String tamano,
    int edad
) {
}
