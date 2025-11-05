package com.Tuki.Tuki_Backend_Provisional.Controladores;

import com.Tuki.Tuki_Backend_Provisional.Entidades.DTOs.UsuarioDTOs.UsuarioUpdateDTO;
import com.Tuki.Tuki_Backend_Provisional.Entidades.Usuario;
import com.Tuki.Tuki_Backend_Provisional.Entidades.DTOs.ErrorDTO;
import com.Tuki.Tuki_Backend_Provisional.Entidades.DTOs.UsuarioDTOs.UsuarioRespuestaDTO;
import com.Tuki.Tuki_Backend_Provisional.Entidades.DTOs.UsuarioDTOs.UsuarioPostDTO;
import com.Tuki.Tuki_Backend_Provisional.Servicios.UsuarioServiceIMP;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    UsuarioServiceIMP usuarioServiceIMP;

    @GetMapping("/activos")
    public List<UsuarioRespuestaDTO> listarActivos(){
        return usuarioServiceIMP.listarActivos();
    }

    @GetMapping("/eliminados")
    public List<UsuarioRespuestaDTO> listarEliminados(){
        return usuarioServiceIMP.listarEliminados();
    }

    @GetMapping("/todos")
    public List<UsuarioRespuestaDTO> listarTodos(){
        return usuarioServiceIMP.listarTodos();
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@Valid @RequestBody UsuarioPostDTO dto){
        return usuarioServiceIMP.registrar(dto);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody UsuarioPostDTO dto){
        return usuarioServiceIMP.login(dto);
    }

    @PatchMapping("/{id}/reactivar")
    public ResponseEntity<?> reactivar(@PathVariable Long id) {
        return usuarioServiceIMP.reactivar(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody UsuarioUpdateDTO dto){
        return usuarioServiceIMP.editar(id, dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        Optional<Usuario> eliminado = usuarioServiceIMP.eliminar(id);
        if (eliminado.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrorDTO("Usuario no encontrado"));
        }
        return ResponseEntity.ok().body("Usuario marcado como eliminado");
    }

}
