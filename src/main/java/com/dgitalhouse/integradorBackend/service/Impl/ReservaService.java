package com.dgitalhouse.integradorBackend.service.Impl;

import com.dgitalhouse.integradorBackend.DTO.entrada.ReservaEntradaDto;
import com.dgitalhouse.integradorBackend.DTO.salida.ReservaSalidaDto;
import com.dgitalhouse.integradorBackend.entity.Habitacion;
import com.dgitalhouse.integradorBackend.entity.Reserva;
import com.dgitalhouse.integradorBackend.entity.Usuario;
import com.dgitalhouse.integradorBackend.repository.HabitacionRepository;
import com.dgitalhouse.integradorBackend.repository.ReservaRepository;
import com.dgitalhouse.integradorBackend.repository.UsuarioRepository;
import com.dgitalhouse.integradorBackend.service.IReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDate;

@Service
public class ReservaService implements IReservaService {
    @Autowired
    private ReservaRepository reservaRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private HabitacionRepository habitacionRepository;


    @Override
    public ResponseEntity<ReservaSalidaDto> registrarReserva(
            ReservaEntradaDto reservaEntradaDto, UriComponentsBuilder uriComponentsBuilder) {

        Usuario usuario = usuarioRepository.findById(reservaEntradaDto.usuarioId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Habitacion habitacion = habitacionRepository.findById(reservaEntradaDto.habitacionId())
        .orElseThrow(() -> new RuntimeException("Habitacion no encontrada"));

        LocalDate fechaCreacion = LocalDate.now();

        LocalDate fechaEntrada = LocalDate.parse(reservaEntradaDto.getFechaEntrada());
        LocalDate fechaSalida = LocalDate.parse(reservaEntradaDto.getFechaSalida());

        if (fechaEntrada.isBefore(LocalDate.now())) {
            throw new RuntimeException("La fecha de entrada debe ser posterior a la fecha actual");
        }

        if (fechaEntrada.isAfter(fechaSalida)) {
            throw new RuntimeException("La fecha de entrada debe ser anterior a la fecha de salida");
        }

        if (!habitacionEstaDisponible(habitacion, fechaEntrada, fechaSalida) ) {
            throw new RuntimeException("La habitacion no esta disponible en las fechas indicadas");
        }

        Double precioTotal = habitacion.getPrecioUnitario() * (fechaSalida.toEpochDay() - fechaEntrada.toEpochDay());

        Reserva reserva = new Reserva(reservaEntradaDto, usuario,habitacion, precioTotal);

        ReservaSalidaDto reservaSalidaDto = new ReservaSalidaDto(reservaRepository.save(reserva));

        return ResponseEntity.created(uriComponentsBuilder.path("/reservas/{id}")
                .buildAndExpand(reservaSalidaDto.id()).toUri()).body(reservaSalidaDto);
    }

    private boolean habitacionEstaDisponible(Habitacion habitacion, LocalDate fechaEntrada, LocalDate fechaSalida) {
        for (Reserva reserva : habitacion.getReservas()) {
            if (reserva.getFechaEntrada().isBefore(fechaSalida) && reserva.getFechaSalida().isAfter(fechaEntrada)) {
                return false;
            }
        }
        return true;
    }
}
