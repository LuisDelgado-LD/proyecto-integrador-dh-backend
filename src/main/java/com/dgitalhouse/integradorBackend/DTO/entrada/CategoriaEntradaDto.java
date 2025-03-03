package com.dgitalhouse.integradorBackend.DTO.entrada;

import com.dgitalhouse.integradorBackend.entity.Imagen;
import com.dgitalhouse.integradorBackend.entity.enums.TamanoHabitacion;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record CategoriaEntradaDto(

        Long id,
        @NotBlank
        String nombre,
        @NotBlank
        String descripcion,
        String imagenUrl
)

{

}



