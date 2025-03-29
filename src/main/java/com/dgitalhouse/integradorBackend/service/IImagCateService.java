package com.dgitalhouse.integradorBackend.service;

import com.dgitalhouse.integradorBackend.entity.Categoria;
import com.dgitalhouse.integradorBackend.entity.Imagen;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collection;

public interface IImagCateService {

    Categoria subirImage(Long id, MultipartFile file) throws RuntimeException;

    Categoria findById(Long id);


}
