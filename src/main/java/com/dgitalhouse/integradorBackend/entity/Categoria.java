package com.dgitalhouse.integradorBackend.entity;

import com.dgitalhouse.integradorBackend.DTO.entrada.CategoriaEntradaDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table (name = "categorias")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Categoria {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (length = 100, nullable = false, unique = true)
    private String nombre;

    @Column (length = 400, nullable = false)
    private String descripcion;

    @Column(length = 300, nullable = true )
    private String imagenUrl;

    public Categoria(CategoriaEntradaDto categoriaEntradaDto) {
        this.nombre = categoriaEntradaDto.nombre();
        this.descripcion = categoriaEntradaDto.descripcion();
        this.imagenUrl = categoriaEntradaDto.imagenUrl();
    }

    public void setImagenUrl(String imagenUrl) {
        this.imagenUrl = imagenUrl;
    }
}
