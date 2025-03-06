package com.dgitalhouse.integradorBackend.service.Impl;

import com.dgitalhouse.integradorBackend.DTO.entrada.HabitacionEntradaDto;
import com.dgitalhouse.integradorBackend.DTO.salida.HabitacionSalidaDto;
import com.dgitalhouse.integradorBackend.entity.Caracteristicas;
import com.dgitalhouse.integradorBackend.entity.Categoria;
import com.dgitalhouse.integradorBackend.entity.Habitacion;
import com.dgitalhouse.integradorBackend.entity.Imagen;
import com.dgitalhouse.integradorBackend.repository.CaracteristicasRepository;
import com.dgitalhouse.integradorBackend.repository.CategoriaRepository;
import com.dgitalhouse.integradorBackend.repository.HabitacionRepository;
import com.dgitalhouse.integradorBackend.service.IHabitacionService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class HabitacionService implements IHabitacionService {

    @Autowired
    private HabitacionRepository habitacionRepository;
    @Autowired
    private CloudinaryService cloudinaryService;
    @Autowired
    private CategoriaRepository categoriaRepository;

    //@Autowired
    //private CaracteristicasRepository caracteristicasRepository;

    @Override
    public ResponseEntity<HabitacionSalidaDto> registrarHabitacion(HabitacionEntradaDto habitacionEntradaDto, UriComponentsBuilder uriComponentsBuilder) {
        Habitacion habitacion = new Habitacion(habitacionEntradaDto);
        Categoria categoria = categoriaRepository.findById(habitacion.getCategoria().getId()).orElse(null);
        if (categoria == null) {
            throw new DataIntegrityViolationException("La categoría no existe");
        }
        habitacion.setCategoria(categoria);
        try {
            HabitacionSalidaDto habitacionSalidaDto = new HabitacionSalidaDto(habitacionRepository.save(habitacion));
            return ResponseEntity.created(uriComponentsBuilder.path("/habitaciones/{id}").buildAndExpand(habitacionSalidaDto.id()).toUri()).body(habitacionSalidaDto);
        } catch (DataIntegrityViolationException ex) {
            throw new DataIntegrityViolationException("El nombre ya está en uso");

        }
    }

    @Override
    public ResponseEntity<Page<HabitacionSalidaDto>> listar(Pageable pagination) {
        //int indiceAleatorio = new Random().nextInt((int)habitacionRepository.count());
        //return ResponseEntity.ok((habitacionRepository.findAll(PageRequest.of(indiceAleatorio, pagination.getPageSize(), Sort.unsorted()))).map(HabitacionSalidaDto::new));
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
        if (habitacionRepository.existsById(id)) {
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

    //public Habitacion agregarCaracteristicasAHabitacion(Long habitacionId, List<Long> caracteristicasIds) {
    //  Habitacion habitacion = habitacionRepository.findById(habitacionId)
    //        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, ("Habitación no encontrada" + habitacionId)));

    //List<Caracteristicas> caracteristicas = caracteristicasRepository.findAllById(caracteristicasIds);

    //if (caracteristicas.isEmpty()) {
    //  throw new ResponseStatusException(HttpStatus.NOT_FOUND, ("No se encontraron categorías con los IDs proporcionados"));
    //}

    //habitacion.getCaracteristicas().addAll(caracteristicas);
    // Habitacion habitacionActualizada = habitacionRepository.save(habitacion);

    //return ResponseEntity.ok(habitacionActualizada).getBody();
    //}

}