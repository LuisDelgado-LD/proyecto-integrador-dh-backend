package com.dgitalhouse.integradorBackend.service;

import com.dgitalhouse.integradorBackend.DTO.entrada.HabitacionEntradaDto;
import com.dgitalhouse.integradorBackend.DTO.salida.HabitacionSalidaDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;


public interface IHabitacionService {
    ResponseEntity<HabitacionSalidaDto> registrarHabitacion(HabitacionEntradaDto habitacionEntradaDto, UriComponentsBuilder uriComponentsBuilder);

    ResponseEntity<Page<HabitacionSalidaDto>> listar(Pageable pagination);


    ResponseEntity<Page<HabitacionSalidaDto>> listarDisponibles(Pageable pagination);

    ResponseEntity<HabitacionSalidaDto> obtenerHabitacion(Long id);

    ResponseEntity<HabitacionSalidaDto> actualizarHabitacion(Long id, HabitacionEntradaDto habitacionEntradaDto);

    boolean eliminarHabitacion(Long id);
}
