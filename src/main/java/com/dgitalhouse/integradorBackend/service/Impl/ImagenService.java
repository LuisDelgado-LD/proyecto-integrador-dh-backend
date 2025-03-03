package com.dgitalhouse.integradorBackend.service.Impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.dgitalhouse.integradorBackend.DTO.entrada.ImagenEntradaDto;
import com.dgitalhouse.integradorBackend.entity.Habitacion;
import com.dgitalhouse.integradorBackend.entity.Imagen;
import com.dgitalhouse.integradorBackend.exception.InternalServerErrorException;
import com.dgitalhouse.integradorBackend.repository.HabitacionRepository;
import com.dgitalhouse.integradorBackend.repository.ImagenRepository;
import com.dgitalhouse.integradorBackend.service.IImagenService;
import org.apache.commons.io.FilenameUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
public class ImagenService implements IImagenService {

    private Cloudinary cloudinary;
    private ImagenRepository imagenRepository;
    private HabitacionRepository habitacionRepository;

    public ImagenService(Cloudinary cloudinary, ImagenRepository imagenRepository, HabitacionRepository habitacionRepository) {
        this.cloudinary = cloudinary;
        this.imagenRepository = imagenRepository;
        this.habitacionRepository = habitacionRepository;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Imagen uploadImage(Long id, MultipartFile file) throws RuntimeException {

        List<String> extencionesPermitidas = Arrays.asList("jpg", "jpeg", "png", "webp", "avif");
        if (!extencionesPermitidas.contains(FilenameUtils.getExtension(file.getOriginalFilename()))) {
            throw new RuntimeException("Formato de imagen no permitido");
        }
        Habitacion habitacion = habitacionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Habitación no encontrada"));
        try {
            Map<String, Object> resultUpload = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.asMap("folder", "habitaciones"));
            String imagenUrl = resultUpload.get("secure_url").toString();
            Imagen imagen = Imagen.builder()
                    .url(imagenUrl)
                    .habitacion(habitacion)
                    .build();
            return imagenRepository.save(imagen);
        } catch (Exception e) {
            throw new InternalServerErrorException(e.getMessage());
        }


    }

    @Override
    public Imagen findById(Long id) {
        return imagenRepository.findById(id).orElse(null);
    }

    @Override
    public List<Imagen> findAll() {
        return imagenRepository.findAll();
    }

    @Override
    public boolean eliminarImagen(Long id) {
        if (imagenRepository.existsById(id)) {
            imagenRepository.deleteById(id);
            return true;
        }
        return false;
    }

 }
