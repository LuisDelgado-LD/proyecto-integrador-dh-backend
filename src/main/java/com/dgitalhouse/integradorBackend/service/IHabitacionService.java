package com.dgitalhouse.integradorBackend.service;

import com.dgitalhouse.integradorBackend.DTO.entrada.HabitacionEntradaDto;
import com.dgitalhouse.integradorBackend.DTO.salida.HabitacionSalidaDto;
import com.dgitalhouse.integradorBackend.entity.Habitacion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;


public interface IHabitacionService {
    ResponseEntity<HabitacionSalidaDto> registrarHabitacion(HabitacionEntradaDto habitacionEntradaDto, UriComponentsBuilder uriComponentsBuilder);

    ResponseEntity<Page<HabitacionSalidaDto>> listar(Pageable pagination);


    ResponseEntity<Page<HabitacionSalidaDto>> listarDisponibles(Pageable pagination);

    ResponseEntity<HabitacionSalidaDto> obtenerHabitacion(Long id);

    ResponseEntity<HabitacionSalidaDto> actualizarHabitacion(Long id, HabitacionEntradaDto habitacionEntradaDto);

    boolean eliminarHabitacion(Long id);

    Habitacion agregarCaracteristicasAHabitacion(Long id, List<Long> categoriaIds);

    List<HabitacionSalidaDto> buscarHabitacionesPorTermino(Long categoriaId, LocalDate fechaEntrada, LocalDate fechaSalida);

    List<HabitacionSalidaDto> buscarHabitacionesPorFechas(LocalDate fechaEntrada, LocalDate fechaSalida);


    List<HabitacionSalidaDto> buscarHabitacionesPorNombre(String nombre);
}
