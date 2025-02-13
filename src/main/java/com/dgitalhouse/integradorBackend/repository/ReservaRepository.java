package com.dgitalhouse.integradorBackend.repository;

import com.dgitalhouse.integradorBackend.entity.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Long> {
}
