package com.dgitalhouse.integradorBackend.controller;

import com.dgitalhouse.integradorBackend.entity.Categoria;
import com.dgitalhouse.integradorBackend.entity.Imagen;
import com.dgitalhouse.integradorBackend.service.IImagCateService;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.hc.core5.http.io.HttpServerRequestHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/categorias")
public class ImagenCateController {

    private IImagCateService imagCateService;

    private Categoria categoria;

    @Autowired
    public ImagenCateController(IImagCateService imagCateService) {
        this.imagCateService = imagCateService;
    }

    // 🟢 Subir una imagen a Cloudinary y guardarla en la BD
    @PostMapping("/{id}")
    public ResponseEntity<Map<String, String>> uploadImage(@RequestParam("imagen") MultipartFile file, @PathVariable Long id) {
        try {
            Categoria categoria = imagCateService.subirImage(id, file);
            return ResponseEntity.ok(Map.of("url", categoria.getImagenUrl()));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Map.of("Error al subir la imagen", e.getMessage()));
        }
    }


}