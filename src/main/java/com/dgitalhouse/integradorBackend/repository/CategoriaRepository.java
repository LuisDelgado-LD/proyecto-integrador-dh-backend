package com.dgitalhouse.integradorBackend.repository;

import com.dgitalhouse.integradorBackend.entity.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    Optional<Categoria> findById(Long id);

}