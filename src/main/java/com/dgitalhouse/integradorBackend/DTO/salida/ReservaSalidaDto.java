package com.dgitalhouse.integradorBackend.DTO.salida;

import com.dgitalhouse.integradorBackend.entity.Habitacion;
import com.dgitalhouse.integradorBackend.entity.Mascota;
import com.dgitalhouse.integradorBackend.entity.Usuario;
public record ReservaSalidaDto(
    Long id,
    Usuario usuario,
    Mascota mascota,
    Habitacion habitacion,
    String fechaEntrada,
    String fechaSalida,
    String estado
) {
}
