package com.dgitalhouse.integradorBackend.service.Impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.dgitalhouse.integradorBackend.DTO.entrada.CategoriaEntradaDto;
import com.dgitalhouse.integradorBackend.DTO.salida.CategoriaSalidaDto;
import com.dgitalhouse.integradorBackend.entity.Categoria;
import com.dgitalhouse.integradorBackend.repository.CategoriaRepository;
import com.dgitalhouse.integradorBackend.service.ICategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

//import static com.dgitalhouse.integradorBackend.service.Impl.ImagCateService.obtenerPublicIdDesdeUrl;

@Service
public class CategoriaService implements ICategoriaService {
    @Autowired
    private CategoriaRepository categoriaRepository;
    @Autowired
    private Cloudinary cloudinary;
    @Autowired
    private CloudinaryService cloudinaryService;

    public CategoriaService(Cloudinary cloudinary) {
        this.cloudinary = cloudinary;
    }



    @Override
    public ResponseEntity<CategoriaSalidaDto> registrarCategoria(CategoriaEntradaDto categoriaEntradaDto, UriComponentsBuilder uriComponentsBuilder) {
     Categoria categoria = new Categoria(categoriaEntradaDto);
     CategoriaSalidaDto categoriaSalidaDto = new CategoriaSalidaDto(categoriaRepository.save(categoria));
        return ResponseEntity.created(uriComponentsBuilder.path("/categorias/{id}").buildAndExpand(categoriaSalidaDto.id()).toUri()).body(categoriaSalidaDto);

    }
    @Override
    public ResponseEntity<CategoriaSalidaDto> obtenerCategoria(Long id) {
        return ResponseEntity.ok(new CategoriaSalidaDto(categoriaRepository.getReferenceById(id)));
    }

    @Override
    public List<CategoriaSalidaDto> obtenerTodasCategorias() {
        return categoriaRepository.findAll().stream()
                .map(CategoriaSalidaDto::new)
                .collect(Collectors.toList());

    }

    @Override
    public ResponseEntity<CategoriaSalidaDto> actualizarCategoria(Long id, CategoriaEntradaDto categoriaEntradaDto) {
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));

        if (categoriaEntradaDto.nombre() != null) {
            categoria.setNombre(categoriaEntradaDto.nombre());
        }
        if (categoriaEntradaDto.descripcion() != null) {
            categoria.setDescripcion(categoriaEntradaDto.descripcion());
        }
        if (categoriaEntradaDto.imagenUrl() != null) { // Solo actualizar si no es null
            categoria.setImagenUrl(categoriaEntradaDto.imagenUrl());
        }
        if (categoriaEntradaDto.patitas() != null) {
            categoria.setPatitas(categoriaEntradaDto.patitas());

        }
       Categoria categoriaActualizada = categoriaRepository.save(categoria);

        return ResponseEntity.ok(new CategoriaSalidaDto(categoriaActualizada));

    }

    @Override
    public boolean eliminarCategoria(@PathVariable Long id) {
        Categoria categoria = categoriaRepository.findById(id).orElse(null);
        if (categoria != null) {
            try {
                // Obtener el public_id desde la URL de la imagen
                String publicId = obtenerPublicIdDesdeUrl(categoria.getImagenUrl());

                // Eliminar la imagen de Cloudinary
                if (publicId != null && !publicId.isEmpty()) {
                    Map result = cloudinary.uploader().destroy(publicId, ObjectUtils.emptyMap());
                    System.out.println("Resultado de eliminación en Cloudinary: " + result);
                }

                // Eliminar la categoría de la base de datos
                categoriaRepository.deleteById(id);
                return true;

            } catch (Exception e) {
                throw new RuntimeException("No se pudo eliminar la imagen de Cloudinary", e);
            }
        }
        return false;
    }

    private String obtenerPublicIdDesdeUrl(String imageUrl) {
        if (imageUrl == null || !imageUrl.contains("/upload/")) {
            return null;
        }

        // Extraer todo lo que viene después de "/upload/"
        String sinDominio = imageUrl.substring(imageUrl.indexOf("/upload/") + 8);

        // Eliminar la extensión del archivo (.jpg, .png, etc.)
        return sinDominio.substring(0, sinDominio.lastIndexOf("."));
    }
}

