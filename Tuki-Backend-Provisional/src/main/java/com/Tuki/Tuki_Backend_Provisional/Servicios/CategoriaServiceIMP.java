package com.Tuki.Tuki_Backend_Provisional.Servicios;

import com.Tuki.Tuki_Backend_Provisional.Entidades.Categoria;
import com.Tuki.Tuki_Backend_Provisional.Entidades.DTOs.CategoriaDTOs.CategoriaPostDTO;
import com.Tuki.Tuki_Backend_Provisional.Entidades.DTOs.CategoriaDTOs.CategoriaRespuestaDTO;
import com.Tuki.Tuki_Backend_Provisional.Entidades.DTOs.CategoriaDTOs.CategoriaUpdateDTO;
import com.Tuki.Tuki_Backend_Provisional.Entidades.DTOs.ErrorDTO;
import com.Tuki.Tuki_Backend_Provisional.Repositorys.CategoriaRepository;
import com.Tuki.Tuki_Backend_Provisional.Servicios.IntefacesServicios.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class CategoriaServiceIMP extends BaseServiceImpl<Categoria, Long, CategoriaPostDTO, CategoriaUpdateDTO, CategoriaRespuestaDTO> implements CategoriaService {

    @Autowired
    CategoriaRepository categoriaRepository;

    @Override
    public ResponseEntity<?> registrar(CategoriaPostDTO dto) {
        boolean existe = categoriaRepository.findByNombre(dto.nombre()).isPresent();
        return registrarConValidacion(existe, "Nombre de categoría ya registrado", dto);
    }

    @Override
    public ResponseEntity<?> editar(Long id, CategoriaUpdateDTO dto) {
        Optional<Categoria> categoria = categoriaRepository.findById(id);
        if (categoria.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrorDTO("Categoría no encontrada"));
        }

        if (categoria.get().getEliminado()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "La categoría está eliminada");
        }

        Optional<Categoria> existente = categoriaRepository.findByNombre(dto.nombre());
        if (existente.isPresent() && !existente.get().getId().equals(id)) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(new ErrorDTO("El nombre de categoría ya está en uso"));
        }

        CategoriaRespuestaDTO actualizado = super.actualizar(id, dto);
        return ResponseEntity.ok(actualizado);
    }
}
