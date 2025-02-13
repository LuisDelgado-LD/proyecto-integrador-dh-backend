package com.dgitalhouse.integradorBackend.service.Impl;

import com.dgitalhouse.integradorBackend.DTO.entrada.HabitacionEntradaDto;
import com.dgitalhouse.integradorBackend.DTO.salida.HabitacionSalidaDto;
import com.dgitalhouse.integradorBackend.entity.Habitacion;
import com.dgitalhouse.integradorBackend.repository.HabitacionRepository;
import com.dgitalhouse.integradorBackend.service.IHabitacionService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.data.domain.Pageable;

@Service
public class HabitacionService implements IHabitacionService {

    private HabitacionRepository habitacionRepository;

    public HabitacionService(HabitacionRepository habitacionRepository) {
        this.habitacionRepository = habitacionRepository;
    }

    @Override
    public ResponseEntity<HabitacionSalidaDto> registrarHabitacion(HabitacionEntradaDto habitacionEntradaDto, UriComponentsBuilder uriComponentsBuilder) {
        Habitacion habitacion = new Habitacion(habitacionEntradaDto);
        HabitacionSalidaDto habitacionSalidaDto = new HabitacionSalidaDto(habitacionRepository.save(habitacion));
        return ResponseEntity.created(uriComponentsBuilder.path("/habitaciones/{id}").buildAndExpand(habitacionSalidaDto.id()).toUri()).body(habitacionSalidaDto);
    }

    @Override
    public ResponseEntity<Page<HabitacionSalidaDto>> listar(Pageable pagination) {
        return ResponseEntity.ok(habitacionRepository.findAll(pagination).map(HabitacionSalidaDto::new));
    }

    @Override
    public ResponseEntity<Page<HabitacionSalidaDto>> listarDisponibles(Pageable pagination) {
        return ResponseEntity.ok((habitacionRepository.findByIsDisponibleTrue(pagination)).map(HabitacionSalidaDto::new));
    }

    @Override
    public ResponseEntity<HabitacionSalidaDto> obtenerHabitacion(Long id) {
        return ResponseEntity.ok(new HabitacionSalidaDto(habitacionRepository.getReferenceById(id)));
    }

    @Override
    public ResponseEntity<HabitacionSalidaDto> actualizarHabitacion(Long id, HabitacionEntradaDto habitacionEntradaDto) {
        if (habitacionRepository.existsById(id)){
            Habitacion habitacion = new Habitacion(habitacionEntradaDto);
            habitacion.setId(id);
            return ResponseEntity.ok(new HabitacionSalidaDto(habitacionRepository.save(habitacion)));
        }
        return null;

    }

    @Override
    public boolean eliminarHabitacion(Long id) {
        if (habitacionRepository.existsById(id)) {
            habitacionRepository.deleteById(id);
            return true;
        }
        return false;
    }

}
