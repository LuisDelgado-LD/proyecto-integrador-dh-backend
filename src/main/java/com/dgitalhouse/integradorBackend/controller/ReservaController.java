package com.dgitalhouse.integradorBackend.controller;

import com.dgitalhouse.integradorBackend.DTO.entrada.ReservaEntradaDto;
import com.dgitalhouse.integradorBackend.DTO.salida.HabitacionSalidaDto;
import com.dgitalhouse.integradorBackend.DTO.salida.ReservaSalidaDto;
import com.dgitalhouse.integradorBackend.service.IReservaService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDate;

@RestController
@RequestMapping("api/reservas")
public class ReservaController {

    private IReservaService reservaService;

    public ReservaController(IReservaService reservaService) {
        this.reservaService = reservaService;
    }

    @PostMapping
    public ResponseEntity<ReservaSalidaDto> registrarReserva(
            @RequestBody @Valid ReservaEntradaDto reservaEntradaDto, UriComponentsBuilder uriComponentsBuilder) {
        System.out.println("aqui");

        return reservaService.registrarReserva(reservaEntradaDto, uriComponentsBuilder);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReservaSalidaDto> obtenerReservaPorId(@PathVariable Long id) {
        return reservaService.obtenerReservaPorId(id);
    }

    @DeleteMapping("/{id}")
    public boolean eliminarReserva(@PathVariable Long id) {
        return reservaService.eliminarReserva(id);
    }

    @GetMapping("/usuario/{id}")
    public ResponseEntity<Page<ReservaSalidaDto>> listarReservasPorUsuario(@PathVariable Long id){
        return reservaService.listarReservasPorUsuario(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReservaSalidaDto> actualizarReserva(@PathVariable Long id, @RequestBody @Valid ReservaEntradaDto reservaEntradaDto) {
        return reservaService.actualizarReserva(id, reservaEntradaDto);
    }




}
