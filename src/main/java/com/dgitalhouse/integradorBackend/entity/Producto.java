package com.dgitalhouse.integradorBackend.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "productos")
public class Producto {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        @Column(length = 100, nullable = false)
        private String nombre;
        @Column(length = 200, nullable = false)
        private String descripcion;

        @Column(length = 255, nullable = false)
        private String imagen;
        @Column(length = 100, nullable = false)
        private Double precioUnitario;

    }


