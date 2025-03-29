package com.dgitalhouse.integradorBackend.service;

import com.dgitalhouse.integradorBackend.DTO.entrada.ImagenEntradaDto;
import com.dgitalhouse.integradorBackend.entity.Imagen;
import com.dgitalhouse.integradorBackend.exception.InternalServerErrorException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface IImagenService {

    Imagen uploadImage(Long id, MultipartFile imagen) throws InternalServerErrorException;
    Imagen findById(Long id);
    List<Imagen> findAll();

    boolean eliminarImagen(Long id);


    Imagen marcarComoPrincipal(Long imagenId, Long habitacionId);
}
