package com.dgitalhouse.integradorBackend.service;

import com.dgitalhouse.integradorBackend.DTO.entrada.ReservaEntradaDto;
import com.dgitalhouse.integradorBackend.DTO.salida.ReservaSalidaDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDate;

public interface IReservaService {
    ResponseEntity<ReservaSalidaDto> registrarReserva(ReservaEntradaDto reservaEntradaDto, UriComponentsBuilder uriComponentsBuilder);

    ResponseEntity<ReservaSalidaDto> obtenerReservaPorId(Long id);

    boolean eliminarReserva(Long id);


    ResponseEntity<Page<ReservaSalidaDto>> listarReservasPorUsuario(Long id);

    ResponseEntity<ReservaSalidaDto> actualizarReserva(Long id, ReservaEntradaDto reservaEntradaDto);


}
