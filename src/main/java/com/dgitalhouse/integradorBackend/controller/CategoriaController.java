package com.dgitalhouse.integradorBackend.controller;

import com.dgitalhouse.integradorBackend.DTO.entrada.CategoriaEntradaDto;
import com.dgitalhouse.integradorBackend.DTO.salida.CategoriaSalidaDto;
import com.dgitalhouse.integradorBackend.service.ICategoriaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("api/categorias")
public class CategoriaController {

    private ICategoriaService categoriaService;

    public CategoriaController(ICategoriaService categoriaService) { this.categoriaService = categoriaService; }
    @PostMapping
    public ResponseEntity<CategoriaSalidaDto> registrarCategoria(@RequestBody @Valid CategoriaEntradaDto categoriaEntradaDto, UriComponentsBuilder uriComponentsBuilder) {
        {
            return categoriaService.registrarCategoria(categoriaEntradaDto, uriComponentsBuilder);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaSalidaDto> obtenerCategoria(@PathVariable Long id) {
        return categoriaService.obtenerCategoria(id);
    }

    @GetMapping
    public ResponseEntity<List<CategoriaSalidaDto>> obtenerTodasCategorias() {
        return ResponseEntity.ok(categoriaService.obtenerTodasCategorias());
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoriaSalidaDto> actualizarCategoria(@PathVariable Long id, @RequestBody @Valid CategoriaEntradaDto categoriaEntradaDto) {
        return categoriaService.actualizarCategoria(id, categoriaEntradaDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarCategoria(@PathVariable Long id) {
        try {
            boolean eliminada = categoriaService.eliminarCategoria(id);
            if (eliminada) {
                return ResponseEntity.ok("Categoría eliminada correctamente");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Categoría no encontrada o no se pudo eliminar la imagen");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al eliminar la categoría: " + e.getMessage());
        }
    }

    }
