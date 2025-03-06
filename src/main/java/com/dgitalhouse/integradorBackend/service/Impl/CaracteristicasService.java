package com.dgitalhouse.integradorBackend.service.Impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.dgitalhouse.integradorBackend.DTO.entrada.CaracteristicasEntradaDto;
import com.dgitalhouse.integradorBackend.DTO.salida.CaracteristicasSalidaDto;
import com.dgitalhouse.integradorBackend.DTO.salida.CategoriaSalidaDto;
import com.dgitalhouse.integradorBackend.entity.Caracteristicas;
import com.dgitalhouse.integradorBackend.repository.CaracteristicasRepository;
import com.dgitalhouse.integradorBackend.service.ICaracteristicasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
public class CaracteristicasService implements ICaracteristicasService {
    @Autowired
    private CaracteristicasRepository caracteristicasRepository;
    @Autowired
    private Cloudinary cloudinary;
    @Autowired
    private CloudinaryService cloudinaryService;

    public CaracteristicasService(Cloudinary cloudinary) {
        this.cloudinary = cloudinary;
    }


    @Override
    public ResponseEntity<CaracteristicasSalidaDto> registrarCaracteristicas(
            String nombre, MultipartFile icono, UriComponentsBuilder uriComponentsBuilder) {

        String iconoUrl;
        try {
            Map uploadResult = cloudinary.uploader().upload(icono.getBytes(),
                    ObjectUtils.asMap("folder", "caracteristicas"));

            iconoUrl = uploadResult.get("secure_url").toString();
        } catch (IOException e) {
            throw new RuntimeException("Error al subir la imagen a Cloudinary", e);
        }

        // 2️⃣ Crear la entidad Caracteristicas con la URL del icono
        Caracteristicas caracteristicas = new Caracteristicas();
        caracteristicas.setNombre(nombre);
        caracteristicas.setIconoUrl(iconoUrl);

        // 3️⃣ Guardar en la base de datos
        CaracteristicasSalidaDto salidaDto = new CaracteristicasSalidaDto(caracteristicasRepository.save(caracteristicas));

        return ResponseEntity.created(uriComponentsBuilder.path("/caracteristicas/{id}")
                .buildAndExpand(salidaDto.id()).toUri()).body(salidaDto);
    }

    @Override
    public ResponseEntity<CaracteristicasSalidaDto> obtenerCaracteristicasPorId(Long id) {
        return ResponseEntity.ok(new CaracteristicasSalidaDto(caracteristicasRepository.getReferenceById(id)));
    }

    @Override
    public ResponseEntity<List<CaracteristicasSalidaDto>> obtenerTodasCaracteristicas() {
        return ResponseEntity.ok().body(caracteristicasRepository.findAll().stream().map(CaracteristicasSalidaDto::new).toList());
    }

    @Override
    public ResponseEntity<CaracteristicasSalidaDto> actualizarCaracteristicas(Long id, String nombre, MultipartFile icono) {
        Caracteristicas caracteristica = caracteristicasRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Característica no encontrada"));

        String iconoUrl;
        try {
            Map uploadResult = cloudinary.uploader().upload(icono.getBytes(),
                    ObjectUtils.asMap("folder", "caracteristicas"));

            iconoUrl = uploadResult.get("secure_url").toString();
        } catch (IOException e) {
            throw new RuntimeException("Error al subir la imagen a Cloudinary", e);
        }

        // 2️⃣ Actualizar los valores
        caracteristica.setNombre(nombre);
        caracteristica.setIconoUrl(iconoUrl);

        // 3️⃣ Guardar cambios
        CaracteristicasSalidaDto salidaDto = new CaracteristicasSalidaDto(caracteristicasRepository.save(caracteristica));

        return ResponseEntity.ok(salidaDto);

    }

    @Override
    public boolean eliminarCaracteristicas(Long id) {

        Caracteristicas caracteristica = caracteristicasRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Característica no encontrada"));

        // 1️⃣ Obtener la URL de la imagen
        String iconoUrl = caracteristica.getIconoUrl();

        System.out.println("uno: " + iconoUrl);
        if (iconoUrl != null && !iconoUrl.isEmpty()) {

            System.out.println("dos: " + iconoUrl);
            // Extraer public_id manualmente sin modificar CloudinaryService
            String publicId = obtenerPublicIdDesdeUrl(iconoUrl);

            System.out.println("tres: " + publicId );
            // Llamar al método de eliminación de Cloudinary
            boolean eliminada = cloudinaryService.eliminarImagen(publicId);

            if (!eliminada) {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "No se pudo eliminar la imagen de Cloudinary");
            }
        }

        // 2️⃣ Eliminar la característica de la base de datos
        caracteristicasRepository.delete(caracteristica);

        return ResponseEntity.ok("Característica eliminada correctamente.").hasBody();
    }
    private String obtenerPublicIdDesdeUrl(String imageUrl) {
        if (imageUrl == null || !imageUrl.contains("/upload/")) {
            return null;
        }
        String sinDominio = imageUrl.substring(imageUrl.indexOf("/caracteristicas/") +1);
        String publicId = sinDominio.substring(0, sinDominio.lastIndexOf("."));
        return publicId;
    }

}





