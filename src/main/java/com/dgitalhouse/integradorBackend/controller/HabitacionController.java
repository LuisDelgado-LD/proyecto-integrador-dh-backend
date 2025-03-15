package com.dgitalhouse.integradorBackend.controller;

import com.dgitalhouse.integradorBackend.DTO.entrada.HabitacionEntradaDto;
import com.dgitalhouse.integradorBackend.DTO.salida.HabitacionSalidaDto;
import com.dgitalhouse.integradorBackend.entity.Categoria;
import com.dgitalhouse.integradorBackend.entity.Habitacion;
import com.dgitalhouse.integradorBackend.service.IHabitacionService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDate;
import java.util.List;


@RestController
@RequestMapping("api/habitaciones")
public class HabitacionController {

    private IHabitacionService habitacionService;

    public HabitacionController(IHabitacionService habitacionService) {
        this.habitacionService = habitacionService;
    }


    @PostMapping
    public ResponseEntity<HabitacionSalidaDto> registrarHabitacion(@RequestBody @Valid HabitacionEntradaDto habitacionEntradaDto, UriComponentsBuilder uriComponentsBuilder) {
        System.out.println("uno");
        return habitacionService.registrarHabitacion(habitacionEntradaDto, uriComponentsBuilder);

    }

    @GetMapping("/all")
    public ResponseEntity<Page<HabitacionSalidaDto>> obtenerListaHabitaciones(@PageableDefault(size = 10) Pageable pagination) {
        return habitacionService.listar(pagination);
    }

    @GetMapping
    public ResponseEntity<Page<HabitacionSalidaDto>> obtenerListaHabitacionesDisponibles(@PageableDefault(size = 10) Pageable pagination) {
        return habitacionService.listarDisponibles(pagination);
    }

    @GetMapping("/{id}")
    public ResponseEntity<HabitacionSalidaDto> obtenerHabitacion(@PathVariable Long id) {
        return habitacionService.obtenerHabitacion(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<HabitacionSalidaDto> actualizarHabitacion(@PathVariable Long id, @RequestBody @Valid HabitacionEntradaDto habitacionEntradaDto) {
        return habitacionService.actualizarHabitacion(id, habitacionEntradaDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarHabitacion(@PathVariable Long id) {
        boolean eliminada = habitacionService.eliminarHabitacion(id);

        if (eliminada) {
            return ResponseEntity.ok("Habitación eliminada correctamente");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Habitación no encontrada");
        }
    }

    @PutMapping("/{id}/caracteristicas")
    public ResponseEntity<HabitacionSalidaDto> asociarCaracteristicasAHabitacion(@PathVariable Long id, @RequestBody List<Long> caracteristicasIds) {

    Habitacion habitacionActualizada = habitacionService.agregarCaracteristicasAHabitacion(id, caracteristicasIds);
    HabitacionSalidaDto habitacionSalidaDto = new HabitacionSalidaDto(habitacionActualizada);
    return ResponseEntity.ok(habitacionSalidaDto);
    }

   @GetMapping("/buscar")
    public ResponseEntity<List<HabitacionSalidaDto>> buscarHabitacionesPorTermino(@RequestParam Long categoriaId, LocalDate fechaEntrada, LocalDate fechaSalida) {
       List<HabitacionSalidaDto> habitaciones = habitacionService.buscarHabitacionesPorTermino(categoriaId, fechaEntrada, fechaSalida);

        if (habitaciones.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(habitacionService.buscarHabitacionesPorTermino(categoriaId, fechaEntrada, fechaSalida));
    }

}
