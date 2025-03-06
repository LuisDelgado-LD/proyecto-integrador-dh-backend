package com.dgitalhouse.integradorBackend.DTO.entrada;

import com.dgitalhouse.integradorBackend.entity.Usuario;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record MascotaEntradaDto(

        String nombre,
        @NotBlank
        String raza,
        @NotBlank
        String tamano,
        @NotNull
        int edad,
        @NotNull
        Long usuarioId
        
        ) {

}
