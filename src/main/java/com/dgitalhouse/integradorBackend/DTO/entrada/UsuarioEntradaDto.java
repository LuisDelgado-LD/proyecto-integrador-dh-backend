package com.dgitalhouse.integradorBackend.DTO.entrada;

import jakarta.validation.constraints.NotBlank;

public record UsuarioEntradaDto(
        @NotBlank
        String nombre,
    @NotBlank
    String apellido,
    @NotBlank
    String email,
    @NotBlank
    String telefono,
    @NotBlank
    String direccion,
    @NotBlank
    String password
) {


}
