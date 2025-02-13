package com.dgitalhouse.integradorBackend.repository;

import com.dgitalhouse.integradorBackend.entity.Mascota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MascotaRepository extends JpaRepository<Mascota, Long> {
}
