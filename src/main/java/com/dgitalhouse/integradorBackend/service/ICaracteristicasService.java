package com.dgitalhouse.integradorBackend.service;

import com.dgitalhouse.integradorBackend.DTO.entrada.CaracteristicasEntradaDto;
import com.dgitalhouse.integradorBackend.DTO.salida.CaracteristicasSalidaDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

public interface ICaracteristicasService {

    ResponseEntity<CaracteristicasSalidaDto> registrarCaracteristicas(String nombre, MultipartFile icono, UriComponentsBuilder uriComponentsBuilder);

    ResponseEntity<CaracteristicasSalidaDto> obtenerCaracteristicasPorId(Long id);

    ResponseEntity<List<CaracteristicasSalidaDto>> obtenerTodasCaracteristicas();

    ResponseEntity<CaracteristicasSalidaDto> actualizarCaracteristicas(Long id, String nombre, MultipartFile icono);

    boolean eliminarCaracteristicas(Long id);
}
