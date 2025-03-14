package com.dgitalhouse.integradorBackend.controller;

import com.dgitalhouse.integradorBackend.DTO.entrada.ReservaEntradaDto;
import com.dgitalhouse.integradorBackend.DTO.salida.ReservaSalidaDto;
import com.dgitalhouse.integradorBackend.service.IReservaService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

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

        return reservaService.registrarReserva(reservaEntradaDto, uriComponentsBuilder);
    }
}
