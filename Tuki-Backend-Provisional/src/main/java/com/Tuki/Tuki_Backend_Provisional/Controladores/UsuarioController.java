package com.Tuki.Tuki_Backend_Provisional.Controladores;

import com.Tuki.Tuki_Backend_Provisional.Entidades.Categoria;
import com.Tuki.Tuki_Backend_Provisional.Entidades.UsuarioDTOs.UsuarioDTO;
import com.Tuki.Tuki_Backend_Provisional.Entidades.UsuarioDTOs.UsuarioPostDTO;
import com.Tuki.Tuki_Backend_Provisional.Entidades.Usuario;
import com.Tuki.Tuki_Backend_Provisional.Entidades.UsuarioDTOs.Mappers.UsuarioMapper;
import com.Tuki.Tuki_Backend_Provisional.Servicios.UsuarioServiceIMP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {
    @Autowired
    UsuarioMapper mapper;

    @Autowired
    UsuarioServiceIMP usuarioServiceIMP;

    @GetMapping
    public List<UsuarioDTO> getAll(){
        return usuarioServiceIMP.listar();
    }

    @PostMapping
    public UsuarioDTO create(@RequestBody UsuarioPostDTO dto){
        return usuarioServiceIMP.crear(dto);
    }


    @PutMapping("/{id}")
    public Usuario update(@PathVariable Long id, @RequestBody Usuario usuario){
        usuario.setId(id);
        return null;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
//        repo.deleteById(id);

        //buscamos elementos por id
//        Categoria categoria = new Categoria(); // aca la categoria
//        categoria.setEliminado(true);
//        categoria.eliminarTodosLosProductos();
    }

}
