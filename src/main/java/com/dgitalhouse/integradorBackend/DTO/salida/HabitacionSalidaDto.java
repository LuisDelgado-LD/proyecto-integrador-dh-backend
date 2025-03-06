package com.dgitalhouse.integradorBackend.DTO.salida;

import com.dgitalhouse.integradorBackend.entity.Caracteristicas;
import com.dgitalhouse.integradorBackend.entity.Categoria;
import com.dgitalhouse.integradorBackend.entity.Habitacion;
import com.dgitalhouse.integradorBackend.entity.Imagen;
import com.dgitalhouse.integradorBackend.entity.enums.TamanoHabitacion;
import java.util.List;

public record HabitacionSalidaDto(

    Long id,
    String nombre,
    String descripcion,
    List<Imagen> imagenes,
    Categoria categoria,

    List<Caracteristicas> caracteristicas,
    TamanoHabitacion tamano,
    Boolean isDisponible,
    Double precioUnitario

) {
    public HabitacionSalidaDto(Habitacion habitacion) {

        this(habitacion.getId(), habitacion.getNombre(),
                habitacion.getDescripcion(),
                habitacion.getImagenes(),
                habitacion.getCategoria(),
                habitacion.getCaracteristicas(),
                habitacion.getTamano(),
                habitacion.getIsDisponible(),
                habitacion.getPrecioUnitario());
    }
}
