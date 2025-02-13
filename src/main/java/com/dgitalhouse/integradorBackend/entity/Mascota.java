package com.dgitalhouse.integradorBackend.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table (name = "mascotas")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Mascota {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 30, nullable = false)
    private String nombre;
    @Column(length = 30, nullable = false)
    private String raza;
    @Column(length = 30, nullable = false)
    private String tamano;
    @Column(length = 30, nullable = false)
    private int edad;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)

    private Usuario usuario;

}
