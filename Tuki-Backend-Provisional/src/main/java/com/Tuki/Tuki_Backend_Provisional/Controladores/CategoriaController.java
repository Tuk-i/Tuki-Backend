package com.Tuki.Tuki_Backend_Provisional.Controladores;

import com.Tuki.Tuki_Backend_Provisional.Entidades.Categoria;
import com.Tuki.Tuki_Backend_Provisional.Entidades.DTOs.CategoriaDTOs.CategoriaPostDTO;
import com.Tuki.Tuki_Backend_Provisional.Entidades.DTOs.CategoriaDTOs.CategoriaRespuestaDTO;
import com.Tuki.Tuki_Backend_Provisional.Entidades.DTOs.CategoriaDTOs.CategoriaUpdateDTO;
import com.Tuki.Tuki_Backend_Provisional.Entidades.DTOs.ErrorDTO;
import com.Tuki.Tuki_Backend_Provisional.Servicios.CategoriaServiceIMP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {

    @Autowired
    CategoriaServiceIMP categoriaService;

    @GetMapping("/activos")
    public List<CategoriaRespuestaDTO> listarActivos() {
        return categoriaService.listarActivos();
    }

    @GetMapping("/eliminados")
    public List<CategoriaRespuestaDTO> listarEliminados() {
        return categoriaService.listarEliminados();
    }

    @GetMapping("/todos")
    public List<CategoriaRespuestaDTO> listarTodos() {
        return categoriaService.listarTodos();
    }

    @PostMapping
    public ResponseEntity<?> crear(@RequestBody CategoriaPostDTO dto) {
        return categoriaService.registrar(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editar(@PathVariable Long id, @RequestBody CategoriaUpdateDTO dto) {
        return categoriaService.editar(id, dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        Optional<Categoria> eliminado = categoriaService.eliminar(id);
        if (eliminado.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrorDTO("Categoría no encontrada"));
        }
        return ResponseEntity.ok("Categoría eliminada correctamente");
    }

    @PatchMapping("/{id}/reactivar")
    public ResponseEntity<?> reactivar(@PathVariable Long id) {
        return categoriaService.reactivar(id);
    }
}