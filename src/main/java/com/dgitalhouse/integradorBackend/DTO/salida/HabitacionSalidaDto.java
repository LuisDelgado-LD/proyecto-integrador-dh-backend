package com.dgitalhouse.integradorBackend.DTO.salida;

import com.dgitalhouse.integradorBackend.entity.Habitacion;
import com.dgitalhouse.integradorBackend.entity.Imagen;
import com.dgitalhouse.integradorBackend.entity.enums.TamanoHabitacion;


public record HabitacionSalidaDto(

    Long id,
    String nombre,
    String descripcion,

    TamanoHabitacion tamano,
    Boolean isDisponible,
    Double precioUnitario

) {
    public HabitacionSalidaDto(Habitacion habitacion) {

        this(habitacion.getId(), habitacion.getNombre(), habitacion.getDescripcion(), habitacion.getTamano(), habitacion.getIsDisponible(), habitacion.getPrecioUnitario());
    }
}
