package com.dgitalhouse.integradorBackend.DTO.entrada;

import jakarta.validation.constraints.NotNull;

public record ReservaEntradaDto(

        Long usuarioId,
        @NotNull
        Long habitacionId,
        String fechaEntrada,

        String fechaSalida

) {

        public CharSequence getFechaEntrada() {
                return fechaEntrada;
        }

        public CharSequence getFechaSalida() {
                return fechaSalida;
        }
}
