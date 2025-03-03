package com.dgitalhouse.integradorBackend.entity;

import com.dgitalhouse.integradorBackend.DTO.entrada.HabitacionEntradaDto;
import com.dgitalhouse.integradorBackend.DTO.salida.HabitacionSalidaDto;
import com.dgitalhouse.integradorBackend.entity.enums.TamanoHabitacion;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import lombok.*;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "habitaciones")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Habitacion {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(length = 100, nullable = false, unique = true)
        private String nombre;

        @Column(length = 200, nullable = false)
        private String descripcion;

        @OneToMany(mappedBy = "habitacion", cascade = CascadeType.ALL, orphanRemoval = true)
        @JsonManagedReference
        private List<Imagen> imagenes = new ArrayList<>();

        @Column(length = 30, nullable = false)
        private TamanoHabitacion tamano;

        @Column(nullable = false)
        private Boolean isDisponible;

        @Column(nullable = false, precision = 10)
        private Double precioUnitario;

        @ManyToOne
        @JoinColumn(name = "categoria_id", nullable = true)
        private Categoria categoria;


        public Habitacion(HabitacionEntradaDto habitacionEntradaDto) {
                this.nombre = habitacionEntradaDto.nombre();
                this.descripcion = habitacionEntradaDto.descripcion();
                this.tamano = habitacionEntradaDto.tamano();
                this.isDisponible = habitacionEntradaDto.isDisponible();
                this.precioUnitario = habitacionEntradaDto.precioUnitario();
                this.categoria = habitacionEntradaDto.categoria();
        }
}



