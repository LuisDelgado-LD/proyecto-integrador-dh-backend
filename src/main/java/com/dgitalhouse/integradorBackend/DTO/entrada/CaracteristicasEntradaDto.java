package com.dgitalhouse.integradorBackend.DTO.entrada;

import jakarta.validation.constraints.NotBlank;

public record CaracteristicasEntradaDto(
        Long id,
        @NotBlank
        String nombre,
        String icono
) {

}
