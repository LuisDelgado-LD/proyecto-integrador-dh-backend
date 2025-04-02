package com.dgitalhouse.integradorBackend.service.Impl;

import com.dgitalhouse.integradorBackend.DTO.entrada.ReservaEntradaDto;
import com.dgitalhouse.integradorBackend.DTO.salida.ReservaSalidaDto;
import com.dgitalhouse.integradorBackend.entity.Caracteristicas;
import com.dgitalhouse.integradorBackend.entity.Habitacion;
import com.dgitalhouse.integradorBackend.entity.Reserva;
import com.dgitalhouse.integradorBackend.entity.Usuario;
import com.dgitalhouse.integradorBackend.repository.HabitacionRepository;
import com.dgitalhouse.integradorBackend.repository.ReservaRepository;
import com.dgitalhouse.integradorBackend.repository.UsuarioRepository;
import com.dgitalhouse.integradorBackend.service.IReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDate;
import java.util.*;

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

        Reserva reserva = new Reserva(reservaEntradaDto, usuario, habitacion, precioTotal);

        ReservaSalidaDto reservaSalidaDto = new ReservaSalidaDto(reservaRepository.save(reserva));

        return ResponseEntity.created(uriComponentsBuilder.path("/reservas/{id}")
                .buildAndExpand(reservaSalidaDto.id()).toUri()).body(reservaSalidaDto);
    }

    @Override
    public ResponseEntity<ReservaSalidaDto> obtenerReservaPorId(Long id) {
        Reserva reserva = reservaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reserva no encontrada"));
        System.out.println("uno: "+ reserva);

        ReservaSalidaDto reservaDto = new ReservaSalidaDto(reserva);
        System.out.println("dos: "+ reservaDto);

        return ResponseEntity.ok(reservaDto);
    }

    @Override
    public boolean eliminarReserva(Long id) {
        Reserva reserva = reservaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Característica no encontrada"));
        reservaRepository.delete(reserva);
        return ResponseEntity.ok("Reserva eliminada correctamente.").hasBody();
    }

    @Override
    public ResponseEntity<Page<ReservaSalidaDto>> listarReservasPorUsuario(Long id) {
           Page<Reserva> reservas = reservaRepository.findByUsuarioId(id, Pageable.ofSize(10));
            if (!reservas.isEmpty()) {
                return ResponseEntity.ok(reservas.map(ReservaSalidaDto::new));
            }

            return ResponseEntity.ok(Page.empty());

    }

    @Override
    public ResponseEntity<ReservaSalidaDto> actualizarReserva(Long id, ReservaEntradaDto reservaEntradaDto) {
        Reserva reserva = reservaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reserva no encontrada"));

        Habitacion habitacion = habitacionRepository.findById(reservaEntradaDto.habitacionId())
                .orElseThrow(() -> new RuntimeException("Habitacion no encontrada"));

        LocalDate fechaEntrada = LocalDate.parse(reservaEntradaDto.getFechaEntrada());
        LocalDate fechaSalida = LocalDate.parse(reservaEntradaDto.getFechaSalida());

        if (fechaEntrada.isBefore(LocalDate.now())) {
            throw new RuntimeException("La fecha de entrada debe ser posterior a la fecha actual.");
        }

        if (fechaEntrada.isAfter(fechaSalida)) {
            throw new RuntimeException("La fecha de entrada debe ser anterior a la fecha de salida.");
        }

        if (!esHabitacionDisponibleParaActualizarReserva(habitacion, fechaEntrada, fechaSalida, reserva.getId())) {
            ResponseEntity.status(HttpStatus.CONFLICT).body("La habitación no está disponible en las fechas indicadas.");

        }

        Double precioTotal = habitacion.getPrecioUnitario() * (fechaSalida.toEpochDay() - fechaEntrada.toEpochDay());

        reserva.setFechaEntrada(fechaEntrada);
        reserva.setFechaSalida(fechaSalida);
        reserva.setPrecioTotal(precioTotal);

        Reserva reservaActualizada = reservaRepository.save(reserva);

        return ResponseEntity.ok(new ReservaSalidaDto(reservaActualizada));
    }


    private boolean habitacionEstaDisponible(Habitacion habitacion, LocalDate fechaEntrada, LocalDate fechaSalida) {
        for (Reserva reserva : habitacion.getReservas()) {
            if (reserva.getFechaEntrada().isBefore(fechaSalida) && reserva.getFechaSalida().isAfter(fechaEntrada)) {
                return false;
            }
        }
        return true;
    }

    private boolean esHabitacionDisponibleParaActualizarReserva(Habitacion habitacion, LocalDate fechaEntrada, LocalDate fechaSalida, Long reservaId) {
        List<Reserva> reservas = reservaRepository.findReservasEnRangoDeFechas(habitacion.getId(), fechaEntrada, fechaSalida);

        // Excluir la reserva que se está actualizando
        return reservas.stream().noneMatch(reserva -> !Objects.equals(reserva.getId(), reservaId));
    }

}
