package com.dgitalhouse.integradorBackend.controller;

import com.dgitalhouse.integradorBackend.DTO.entrada.ConsultaPorFechaYNombre;
import com.dgitalhouse.integradorBackend.DTO.entrada.HabitacionEntradaDto;
import com.dgitalhouse.integradorBackend.DTO.salida.HabitacionSalidaDto;
import com.dgitalhouse.integradorBackend.entity.Habitacion;
import com.dgitalhouse.integradorBackend.service.IHabitacionService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;


import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@PreAuthorize("denyAll()")
@RequestMapping("api/habitaciones")
public class HabitacionController {

    private IHabitacionService habitacionService;

    public HabitacionController(IHabitacionService habitacionService) {
        this.habitacionService = habitacionService;
    }


    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<HabitacionSalidaDto> registrarHabitacion(@RequestBody @Valid HabitacionEntradaDto habitacionEntradaDto, UriComponentsBuilder uriComponentsBuilder) {
        System.out.println("uno");
        return habitacionService.registrarHabitacion(habitacionEntradaDto, uriComponentsBuilder);

    }

    @GetMapping("/all")
    @PreAuthorize("permitAll()")
    public ResponseEntity<Page<HabitacionSalidaDto>> obtenerListaHabitaciones(@PageableDefault(size = 10) Pageable pagination) {
        return habitacionService.listar(pagination);
    }

    @GetMapping
    @PreAuthorize("permitAll()")
    public ResponseEntity<Page<HabitacionSalidaDto>> obtenerListaHabitacionesDisponibles(@PageableDefault(size = 10) Pageable pagination) {
        return habitacionService.listarDisponibles(pagination);
    }

    @GetMapping("/{id}")
    @PreAuthorize("permitAll()")
    public ResponseEntity<HabitacionSalidaDto> obtenerHabitacion(@PathVariable Long id) {
        return habitacionService.obtenerHabitacion(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<HabitacionSalidaDto> actualizarHabitacion(@PathVariable Long id, @RequestBody @Valid HabitacionEntradaDto habitacionEntradaDto) {
        return habitacionService.actualizarHabitacion(id, habitacionEntradaDto);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> eliminarHabitacion(@PathVariable Long id) {
        boolean eliminada = habitacionService.eliminarHabitacion(id);

        if (eliminada) {
            return ResponseEntity.ok("Habitación eliminada correctamente");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Habitación no encontrada");
        }
    }

    @PutMapping("/{id}/caracteristicas")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<HabitacionSalidaDto> asociarCaracteristicasAHabitacion(@PathVariable Long id, @RequestBody List<Long> caracteristicasIds) {

        Habitacion habitacionActualizada = habitacionService.agregarCaracteristicasAHabitacion(id, caracteristicasIds);
        HabitacionSalidaDto habitacionSalidaDto = new HabitacionSalidaDto(habitacionActualizada);
        return ResponseEntity.ok(habitacionSalidaDto);
    }

   @GetMapping("/buscar")
    @PreAuthorize("permitAll()")
    public ResponseEntity<List<HabitacionSalidaDto>> buscarHabitacionesPorNombreyFechas(@RequestBody ConsultaPorFechaYNombre consultaPorFechaYNombre) {
       System.out.println(consultaPorFechaYNombre.entrada());
       System.out.println(consultaPorFechaYNombre.salida());


       return ResponseEntity.ok(habitacionService.buscarHabitacionesPorNombreyFechas(consultaPorFechaYNombre));



   }


    @GetMapping("/disponibles")
    @PreAuthorize("permitAll()")
    public ResponseEntity<?> buscarHabitacionesDisponibles(@RequestParam LocalDate fechaEntrada, LocalDate fechaSalida) {

        List<HabitacionSalidaDto> habitaciones = habitacionService.buscarHabitacionesPorFechas(fechaEntrada, fechaSalida);

        if (habitaciones.isEmpty()) {
            Map<String, String> respuesta = new HashMap<>();
            respuesta.put("mensaje", "No hay habitaciones disponibles en el rango de fechas seleccionado.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
        }

        return ResponseEntity.ok(habitaciones);
    }

@GetMapping("/nombre")
public ResponseEntity<?> buscarPorNombre(@RequestParam String nombre) {
    List<HabitacionSalidaDto> habitaciones = habitacionService.buscarHabitacionesPorNombre(nombre);

    if (habitaciones.isEmpty()) {
        Map<String, String> respuesta = new HashMap<>();
        respuesta.put("mensaje", "No se encontraron habitaciones con el nombre proporcionado.");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
    }

    return ResponseEntity.ok(habitacionService.buscarHabitacionesPorNombre(nombre));
}


}
