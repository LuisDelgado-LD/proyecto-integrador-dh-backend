package com.dgitalhouse.integradorBackend.DTO.salida;

import com.dgitalhouse.integradorBackend.entity.Reserva;
import com.dgitalhouse.integradorBackend.entity.Usuario;

public record ReservaSalidaDto(
    Long id,
    long usuarioId,
    String nombreUsuario,
    Long habitacionId,
    String nombreHabitacion,
    String fechaCreacion,
    String fechaEntrada,
    String fechaSalida,
    String estado,
    Double precioTotal
) {


    public ReservaSalidaDto(Reserva reserva) {
        this(reserva.getId(), reserva.getUsuario().getId(),
                reserva.getUsuario().getNombre(), reserva.getHabitacion().getId(),
                reserva.getHabitacion().getNombre(), reserva.getFechaCreacion().toString(),
                reserva.getFechaEntrada().toString(), reserva.getFechaSalida().toString(),
                reserva.getEstado().toString(), reserva.getPrecioTotal());
    }


}
