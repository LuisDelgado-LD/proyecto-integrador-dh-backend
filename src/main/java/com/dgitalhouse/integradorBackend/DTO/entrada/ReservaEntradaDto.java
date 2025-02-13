package com.dgitalhouse.integradorBackend.DTO.entrada;

import  java.time.LocalDate;
import java.util.Date;
import java.util.List;

import com.dgitalhouse.integradorBackend.entity.Habitacion;
import com.dgitalhouse.integradorBackend.entity.Mascota;
import com.dgitalhouse.integradorBackend.entity.Usuario;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ReservaEntradaDto(
        @NotBlank
        Usuario usuario,
        @NotNull
        Mascota mascota,
    @NotNull
    Habitacion habitacion,
    @Future
    Date fechaEntrada,
    @Future
    Date fechaSalida



) {
}
