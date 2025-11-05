package com.Tuki.Tuki_Backend_Provisional.Controladores;

import com.Tuki.Tuki_Backend_Provisional.Entidades.DTOs.ErrorDTO;
import com.Tuki.Tuki_Backend_Provisional.Entidades.DTOs.ProductoDTOs.ProductoPostDTO;
import com.Tuki.Tuki_Backend_Provisional.Entidades.DTOs.ProductoDTOs.ProductoRespuestaDTO;
import com.Tuki.Tuki_Backend_Provisional.Entidades.DTOs.ProductoDTOs.ProductoUpdateDTO;
import com.Tuki.Tuki_Backend_Provisional.Entidades.Producto;
import com.Tuki.Tuki_Backend_Provisional.Repositorys.BaseRepository;
import com.Tuki.Tuki_Backend_Provisional.Servicios.IntefacesServicios.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @PostMapping
    public ResponseEntity<?> crear(@RequestBody ProductoPostDTO dto) {
        return productoService.registrar(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editar(@PathVariable Long id, @RequestBody ProductoUpdateDTO dto) {
        return productoService.editar(id, dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        Optional<Producto> eliminado = productoService.eliminar(id);
        if (eliminado.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrorDTO("Producto no encontrado"));
        }
        return ResponseEntity.ok("Producto eliminado correctamente");
    }

    @PatchMapping("/{id}/reactivar")
    public ResponseEntity<?> reactivar(@PathVariable Long id) {
        return productoService.reactivar(id);
    }


    @GetMapping("/categoria/{idCategoria}")
    public List<ProductoRespuestaDTO> listarTodos(@PathVariable Long idCategoria) {
        return productoService.productosListarTodos(idCategoria);
    }

    @GetMapping("/categoria/{idCategoria}/activos")
    public List<ProductoRespuestaDTO> listarActivos(@PathVariable Long idCategoria) {
        return productoService.productosListarActivos(idCategoria);
    }

    @GetMapping("/categoria/{idCategoria}/eliminados")
    public List<ProductoRespuestaDTO> listarEliminados(@PathVariable Long idCategoria) {
        return productoService.productosListarEliminados(idCategoria);
    }
}
