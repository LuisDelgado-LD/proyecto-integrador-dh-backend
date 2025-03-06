package com.dgitalhouse.integradorBackend.service.Impl;

import com.dgitalhouse.integradorBackend.DTO.entrada.MascotaEntradaDto;
import com.dgitalhouse.integradorBackend.DTO.salida.CaracteristicasSalidaDto;
import com.dgitalhouse.integradorBackend.DTO.salida.MascotaSalidaDto;
import com.dgitalhouse.integradorBackend.entity.Mascota;
import com.dgitalhouse.integradorBackend.entity.Usuario;
import com.dgitalhouse.integradorBackend.entity.enums.Rol;
import com.dgitalhouse.integradorBackend.repository.MascotaRepository;
import com.dgitalhouse.integradorBackend.repository.UsuarioRepository;
import com.dgitalhouse.integradorBackend.service.IMascotaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@Service
public class MascotaService implements IMascotaService {

    @Autowired
    private MascotaRepository mascotaRepository;
        @Autowired
    private UsuarioRepository usuarioRepository;

    public ResponseEntity<MascotaSalidaDto> registrarMascota (MascotaEntradaDto mascotaEntradaDto, UriComponentsBuilder uriComponentsBuilder) {
        Usuario usuario = usuarioRepository.findById(mascotaEntradaDto.usuarioId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        System.out.println("Recibido: uno ");
        Mascota mascota = new Mascota(mascotaEntradaDto, usuario);
        System.out.println("Recibido: dos  " + mascotaEntradaDto);

        MascotaSalidaDto mascotaSalidaDto = new MascotaSalidaDto(mascotaRepository.save(mascota));
        System.out.println("Recibido: tres  " + mascotaSalidaDto);
        return ResponseEntity.created(uriComponentsBuilder.path("/mascotas/{id}")
                .buildAndExpand(mascotaSalidaDto.id()).toUri()).body(mascotaSalidaDto);


    }

    @Override
    public ResponseEntity<List<MascotaSalidaDto>> obtenerTodasMascotas() {
        return ResponseEntity.ok().body(mascotaRepository.findAll().stream().map(MascotaSalidaDto::new).toList());
    }


}
