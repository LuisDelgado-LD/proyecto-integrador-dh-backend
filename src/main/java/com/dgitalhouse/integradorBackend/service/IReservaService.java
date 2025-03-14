package com.dgitalhouse.integradorBackend.service;

import com.dgitalhouse.integradorBackend.DTO.entrada.ReservaEntradaDto;
import com.dgitalhouse.integradorBackend.DTO.salida.ReservaSalidaDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

public interface IReservaService {
    ResponseEntity<ReservaSalidaDto> registrarReserva(ReservaEntradaDto reservaEntradaDto, UriComponentsBuilder uriComponentsBuilder);
}
