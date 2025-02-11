package com.dgitalhouse.integradorBackend.entity;

import jakarta.persistence.*;

public class Producto {

    @Entity
    @Table(name = "productos")
    public class Producto {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        @Column(length = 30, nullable = false)
        private String nombre;
        @Column(length = 100, nullable = false)
        private String descripcion;

        @Column(length = 100, nullable = false)
        private String precioUnitario;

    }
}

