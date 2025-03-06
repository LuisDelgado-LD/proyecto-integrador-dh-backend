package com.dgitalhouse.integradorBackend.controller;


import com.dgitalhouse.integradorBackend.DTO.salida.CaracteristicasSalidaDto;
import com.dgitalhouse.integradorBackend.service.ICaracteristicasService;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;


@RestController
@RequestMapping("api/caracteristicas")
public class CaracteristicasController {

    private ICaracteristicasService caracteristicasService;

    public CaracteristicasController (ICaracteristicasService caracteristicasService) {this.caracteristicasService = caracteristicasService;}

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<CaracteristicasSalidaDto> registrarCaracteristicas(
            @RequestParam("nombre") String nombre,
            @RequestParam("icono") MultipartFile icono,
            UriComponentsBuilder uriComponentsBuilder) {

        return caracteristicasService.registrarCaracteristicas(nombre, icono, uriComponentsBuilder);
    }

    @GetMapping("{id}")
    public ResponseEntity<CaracteristicasSalidaDto> obtenerCaracteristicasPorId(@PathVariable Long id) {
        return caracteristicasService.obtenerCaracteristicasPorId(id);
    }

    @GetMapping
    public ResponseEntity<List<CaracteristicasSalidaDto>> obtenerTodasCaracteristicas() {
        return caracteristicasService.obtenerTodasCaracteristicas();
    }

   @PutMapping("{id}")
    public ResponseEntity<CaracteristicasSalidaDto> actualizarCaracteristicas(
            @PathVariable Long id, @RequestParam @Valid String nombre, MultipartFile icono) {

        CaracteristicasSalidaDto caracteristicasActualizada = caracteristicasService.actualizarCaracteristicas
                (id, nombre, icono).getBody();
        return ResponseEntity.ok(caracteristicasActualizada);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> eliminarCaracteristicas(@PathVariable Long id) {
        caracteristicasService.eliminarCaracteristicas(id);
        return ResponseEntity.ok("Característica eliminada correctamente.");
    }

}
