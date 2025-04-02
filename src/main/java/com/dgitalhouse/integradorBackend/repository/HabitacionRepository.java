package com.dgitalhouse.integradorBackend.repository;


import aj.org.objectweb.asm.commons.Remapper;
import com.dgitalhouse.integradorBackend.entity.Reserva;
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



    @Query("SELECT h FROM Habitacion h WHERE h.id NOT IN " +
            "(SELECT r.habitacion.id FROM Reserva r WHERE " +
            "(r.fechaEntrada < :fechaSalida AND r.fechaSalida > :fechaEntrada))")
    List<Habitacion> findHabitacionesDisponibles(@Param("fechaEntrada") LocalDate fechaEntrada,
                                                 @Param("fechaSalida") LocalDate fechaSalida);


    List<Habitacion> findByNombreContainingIgnoreCase(String nombre);


    List<Habitacion> findByNombreIgnoreCase(String nombre);
}

