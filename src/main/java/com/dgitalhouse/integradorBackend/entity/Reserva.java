package com.dgitalhouse.integradorBackend.entity;

import com.dgitalhouse.integradorBackend.entity.enums.EstadoReserva;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "reserva")
@NoArgsConstructor
@AllArgsConstructor
@Data

public class Reserva {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "Usuario_id", nullable = false)
    private Usuario usuario;

    //@ManyToOne
    //@JoinColumn(name = "mascota_id", nullable = false)
    //private Mascota mascota;

    @ManyToOne
    @JoinColumn(name = "habitacion_id", nullable = false)
    private Habitacion habitacion;

    @Column (name = "fecha_creacion", nullable = false)
    private LocalDate fechaCreacion;

    @Column (name = "fecha_entrada", nullable = false)
    private LocalDate fechaEntrada;

    @Column (name = "fecha_salida", nullable = false)
    private LocalDate fechaSalida;

    @Column (name = "precio_total", nullable = false)
    private double precioTotal;

    @Column (name = "estado", nullable = false)
    private EstadoReserva estado;

}
