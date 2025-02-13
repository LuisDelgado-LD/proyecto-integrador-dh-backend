package com.dgitalhouse.integradorBackend.repository;


import aj.org.objectweb.asm.commons.Remapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.dgitalhouse.integradorBackend.entity.Habitacion;
import org.springframework.stereotype.Repository;

@Repository
public interface HabitacionRepository extends JpaRepository<Habitacion, Long> {

    Page<Habitacion> findByIsDisponibleTrue(Pageable pagination);
}
