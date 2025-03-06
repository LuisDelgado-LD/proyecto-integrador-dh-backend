package com.dgitalhouse.integradorBackend.entity;

import com.dgitalhouse.integradorBackend.DTO.entrada.CaracteristicasEntradaDto;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "caracteristicas")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Caracteristicas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (length = 30, nullable = false, unique = true)
    public String nombre;

    @Column (length = 300, nullable = false, unique = true)
    public String iconoUrl;

    //@ManyToMany(mappedBy = "caracteristicas")
    //@JsonIgnore // Evita problemas de serialización infinita
    //private List<Habitacion> habitaciones;

    public void setIconoUrl(String iconoUrl) {
        this.iconoUrl = iconoUrl;
    }


}
