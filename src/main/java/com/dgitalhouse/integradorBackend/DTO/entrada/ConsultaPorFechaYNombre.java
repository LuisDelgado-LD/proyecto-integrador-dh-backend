package com.dgitalhouse.integradorBackend.DTO.entrada;

import java.time.LocalDate;

public record ConsultaPorFechaYNombre(
    String nombre,
    LocalDate entrada,
    LocalDate salida
) {
}
