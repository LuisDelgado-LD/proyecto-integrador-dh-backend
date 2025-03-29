package com.dgitalhouse.integradorBackend.service.Impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.dgitalhouse.integradorBackend.entity.Categoria;
import com.dgitalhouse.integradorBackend.exception.InternalServerErrorException;
import com.dgitalhouse.integradorBackend.repository.CategoriaRepository;
import com.dgitalhouse.integradorBackend.service.IImagCateService;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
@Service
public class ImagCateService implements IImagCateService {
    private Cloudinary cloudinary;
    private CategoriaRepository categoriaRepository;

    public ImagCateService(Cloudinary cloudinary, CategoriaRepository categoriaRepository) {
        this.cloudinary = cloudinary;
        this.categoriaRepository = categoriaRepository;
    }
    @Override
    public Categoria subirImage(Long id, MultipartFile file) throws RuntimeException {
        List<String> extencionesPermitidas = Arrays.asList("jpg", "jpeg", "png", "webp", "avif");
        if(!extencionesPermitidas.contains(FilenameUtils.getExtension(file.getOriginalFilename()))){
            throw new RuntimeException("Formato de imagen no permitido");
        }
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoria no encontrada"));
        try {
            Map resultUpload = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.asMap("folder", "categorias"));
            String imagenUrl = resultUpload.get("secure_url").toString();

            categoria.setImagenUrl(imagenUrl);
            return categoriaRepository.save(categoria);
        } catch(Exception e) {
            throw new InternalServerErrorException("Error al subir la imagen: " + e.getMessage());
        }

            }
    @Override
    public Categoria findById(Long id) {
        return categoriaRepository.findById(id).orElse(null);
    }


}
