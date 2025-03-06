package com.dgitalhouse.integradorBackend.entity;

import com.dgitalhouse.integradorBackend.DTO.entrada.MascotaEntradaDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

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
    @JoinColumn(name = "Usuario_id", nullable = false)
    private Usuario usuario;


    public Mascota(MascotaEntradaDto mascotaEntradaDto, Usuario usuario) {
        this.nombre = mascotaEntradaDto.nombre();
        this.raza = mascotaEntradaDto.raza();
        this.tamano = mascotaEntradaDto.tamano();
        this.edad = mascotaEntradaDto.edad();
        this.usuario = usuario;
    }


}
