package com.Tuki.Tuki_Backend_Provisional.Mappers;

import com.Tuki.Tuki_Backend_Provisional.Entidades.DTOs.UsuarioPostDTO;
import com.Tuki.Tuki_Backend_Provisional.Entidades.Usuario;
import org.springframework.stereotype.Component;

@Component
public class UsuarioMapper {

    public Usuario toEntity(UsuarioPostDTO dto){
        Usuario usuario = new Usuario();
        usuario.setEmail(dto.email());
        usuario.setPassword(dto.password());
        usuario.setRol("cliente");
        return usuario;
    }
}
