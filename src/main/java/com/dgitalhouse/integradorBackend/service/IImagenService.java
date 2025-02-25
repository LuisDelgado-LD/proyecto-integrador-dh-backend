package com.dgitalhouse.integradorBackend.service;

import com.dgitalhouse.integradorBackend.entity.Imagen;
import com.dgitalhouse.integradorBackend.exception.InternalServerErrorException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface IImagenService {

    Imagen uploadImage(Long id, MultipartFile imagen) throws InternalServerErrorException;
    Imagen findById(Long id);
    List<Imagen> findAll();
}
