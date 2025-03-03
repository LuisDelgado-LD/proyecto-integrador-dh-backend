package com.dgitalhouse.integradorBackend.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "usuarios")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Getter
@Setter
@ToString
@Builder
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 30, nullable = false, unique = true)
    private String nombre;

    @Column(length = 30, nullable = false)
    private String apellido;

    @Column(length = 60, nullable = false, unique = true)
    private String email;

    @Column(length = 30, nullable = false)
    private String telefono;

    @Column(length = 30, nullable = false)
    private String direccion;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String rol;
}
