package com.dgitalhouse.integradorBackend.repository;

import com.dgitalhouse.integradorBackend.entity.Imagen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ImagenRepository extends JpaRepository<Imagen, Long> {

    List<Imagen> findByHabitacionId(Long habitacionId);
    @Modifying
    @Query("UPDATE Imagen i SET i.esPrincipal = false WHERE i.habitacion.id = :habitacionId")
    void desmarcarImagenPrincipal(@Param("habitacionId") Long habitacionId);
}
