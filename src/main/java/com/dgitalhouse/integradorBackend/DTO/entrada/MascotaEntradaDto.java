package com.dgitalhouse.integradorBackend.DTO.entrada;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record MascotaEntradaDto(
        @NotBlank
        String nombre,
    @NotBlank
    String raza,
    @NotBlank
    String tamano,
    @NotBlank
    int edad
) {
}
