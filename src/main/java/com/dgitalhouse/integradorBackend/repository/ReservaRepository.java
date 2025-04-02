package com.dgitalhouse.integradorBackend.repository;

import com.dgitalhouse.integradorBackend.entity.Reserva;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Long> {

      List<Reserva> findByHabitacionId(Long habitacionId);
      Page<Reserva> findByUsuarioId(Long id, Pageable pageable);

      @Query("SELECT r FROM Reserva r WHERE r.habitacion.id = :habitacionId " +
              "AND ((r.fechaEntrada <= :fechaSalida AND r.fechaSalida >= :fechaEntrada))")
      List<Reserva> findReservasEnRangoDeFechas(@Param("habitacionId") Long habitacionId,
                                                @Param("fechaEntrada") LocalDate fechaEntrada,
                                                @Param("fechaSalida") LocalDate fechaSalida);


    @Query("SELECT r FROM Reserva r WHERE r.habitacion.id = :habitacionId AND " +
            "(r.fechaEntrada BETWEEN :fechaInicio AND :fechaFin OR " +
            "r.fechaSalida BETWEEN :fechaInicio AND :fechaFin OR " +
            ":fechaInicio BETWEEN r.fechaEntrada AND r.fechaSalida)")
    List<Reserva> buscarReservasEnRango(@Param("fechaInicio") LocalDate fechaEntrada,
                                        @Param("fechaFin") LocalDate fechaSalida,
                                        @Param("habitacionId") Long habitacionId);

    //List<Reserva> findReservasEnRangoDeFechas(Long id, LocalDate fechaEntrada, LocalDate fechaSalida);


}
