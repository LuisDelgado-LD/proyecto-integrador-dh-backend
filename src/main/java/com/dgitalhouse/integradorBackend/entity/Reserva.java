package com.dgitalhouse.integradorBackend.entity;

import com.dgitalhouse.integradorBackend.DTO.entrada.ReservaEntradaDto;
import com.dgitalhouse.integradorBackend.entity.enums.EstadoReserva;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "habitacion_id", nullable = false)
    @JsonBackReference
    private Habitacion habitacion;

    @CreationTimestamp
    @Column (name = "fecha_creacion", nullable = false)
    private LocalDate fechaCreacion;

    @Column (name = "fecha_entrada", nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate fechaEntrada;

    @Column (name = "fecha_salida", nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate fechaSalida;

    @Column (name = "precio_total", nullable = false)
    private double precioTotal;

    @Enumerated(EnumType.STRING)
    @Column (name = "estado", nullable = false)
    private EstadoReserva estado;


    public Reserva(ReservaEntradaDto reservaEntradaDto, Usuario usuario, Habitacion habitacion, double precioTotal) {
        this.usuario = usuario;
        this.habitacion = habitacion;
        this.precioTotal = precioTotal;
        this.fechaEntrada = LocalDate.parse(reservaEntradaDto.getFechaEntrada());
        this.fechaSalida = LocalDate.parse(reservaEntradaDto.getFechaSalida());
        this.estado = EstadoReserva.CONFIRMADA;
    }
}
