package com.dgitalhouse.integradorBackend.DTO.entrada;

import com.dgitalhouse.integradorBackend.entity.Categoria;
import com.dgitalhouse.integradorBackend.entity.Imagen;
import com.dgitalhouse.integradorBackend.entity.enums.TamanoHabitacion;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.List;


public record HabitacionEntradaDto(
        @NotBlank
        String nombre,
        @NotBlank
        String descripcion,
        Imagen imagen,
        @NotNull
        Categoria categoria,

    @NotNull
    TamanoHabitacion tamano,
    @NotNull
    Boolean isDisponible,
    @NotNull
    @Positive
    Double precioUnitario

) {
}
