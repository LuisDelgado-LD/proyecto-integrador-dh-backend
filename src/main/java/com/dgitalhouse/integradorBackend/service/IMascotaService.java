package com.dgitalhouse.integradorBackend.service;

import com.dgitalhouse.integradorBackend.DTO.entrada.MascotaEntradaDto;
import com.dgitalhouse.integradorBackend.DTO.salida.MascotaSalidaDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

public interface IMascotaService {
    ResponseEntity<MascotaSalidaDto> registrarMascota(MascotaEntradaDto mascotaEntradaDto, UriComponentsBuilder uriComponentsBuilder);

    ResponseEntity<List<MascotaSalidaDto>> obtenerTodasMascotas();
}
