package com.dgitalhouse.integradorBackend.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import com.dgitalhouse.integradorBackend.entity.Habitacion;

public interface ProductoRepository extends JpaRepository<Habitacion, Long> {

}
