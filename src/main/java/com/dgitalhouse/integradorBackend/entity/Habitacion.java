package com.dgitalhouse.integradorBackend.entity;

import com.dgitalhouse.integradorBackend.entity.enums.TamanoHabitacion;
import jakarta.persistence.*;

import lombok.*;


@Entity
@Table(name = "habitaciones")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Habitacion {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        @Column(length = 100, nullable = false)
        private String nombre;
        @Column(length = 200, nullable = false)
        private String descripcion;
        @Column(length = 255, nullable = false)
        private String imagen;
        @Column(length = 30, nullable = false)
        private TamanoHabitacion tamano;
        @Column(nullable = false)
        private Boolean disponible = true;
        @Column(nullable = false, precision = 10)
        private Double precioUnitario;


           }



