package com.dgitalhouse.integradorBackend.controller;

import com.dgitalhouse.integradorBackend.DTO.entrada.MascotaEntradaDto;
import com.dgitalhouse.integradorBackend.DTO.salida.CaracteristicasSalidaDto;
import com.dgitalhouse.integradorBackend.DTO.salida.MascotaSalidaDto;
import com.dgitalhouse.integradorBackend.service.IMascotaService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("api/mascotas")
public class MascotaController {

    private IMascotaService mascotaService;

    public MascotaController(IMascotaService mascotaService) {
        this.mascotaService = mascotaService;
    }

    @PostMapping
    public ResponseEntity<MascotaSalidaDto> registrarMascota
            (@RequestBody @Valid MascotaEntradaDto mascotaEntradaDto,
             UriComponentsBuilder uriComponentsBuilder) {

        return mascotaService.registrarMascota(mascotaEntradaDto, uriComponentsBuilder);
    }

    @GetMapping("/all")
    public ResponseEntity<List<MascotaSalidaDto>> obtenerTodasMascotas() {
        return mascotaService.obtenerTodasMascotas();
    }


}
