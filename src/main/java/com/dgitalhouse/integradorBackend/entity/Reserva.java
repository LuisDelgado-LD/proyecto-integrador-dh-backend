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
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "mascota_id", nullable = false)
    private Mascota mascota;

    @ManyToOne
    @JoinColumn(name = "habitacion_id", nullable = false)
    private Habitacion habitacion;

    @Column (nullable = false)
    private LocalDate fechaEntrada;
    @Column (nullable = false)
    private LocalDate fechaSalida;
    @Column (nullable = false)
    private EstadoReserva estado;

}
