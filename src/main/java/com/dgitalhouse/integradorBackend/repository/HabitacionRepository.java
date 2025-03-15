package com.dgitalhouse.integradorBackend.repository;


import aj.org.objectweb.asm.commons.Remapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.dgitalhouse.integradorBackend.entity.Habitacion;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface HabitacionRepository extends JpaRepository<Habitacion, Long> {

    Page<Habitacion> findByIsDisponibleTrue(Pageable pagination);

    @Query("SELECT h FROM Habitacion h WHERE h.categoria.id = :categoriaId " +
            "AND NOT EXISTS (" +
            "    SELECT r FROM Reserva r " +
            "    WHERE r.habitacion = h " +
            "    AND (:fechaEntrada < r.fechaSalida AND :fechaSalida > r.fechaEntrada)" +
            ")")
    List<Habitacion> findAvailableRooms(@Param("categoriaId") Long categoriaId,
                                        @Param("fechaEntrada") LocalDate fechaEntrada,
                                        @Param("fechaSalida") LocalDate fechaSalida);


}
