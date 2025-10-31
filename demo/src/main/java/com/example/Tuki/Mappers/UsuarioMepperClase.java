package com.example.Tuki.Mappers;

import com.example.Tuki.Entitys.DTOs.UsuarioDTO;
import com.example.Tuki.Entitys.DTOs.UsuarioRespuestaDTO;
import com.example.Tuki.Entitys.Enum.Rol;
import com.example.Tuki.Entitys.Usuario;

public class UsuarioMepperClase {
    public static Usuario dtoToEntity(UsuarioDTO dto){
        Usuario usuario = new Usuario();
        usuario.setEmail(dto.correo());
        usuario.setPassword(dto.password());
        usuario.setRol(Rol.CLIENTE);
        return usuario;
    }

    public static UsuarioRespuestaDTO entityToRespuestaDTO(Usuario u){
        UsuarioRespuestaDTO dto = new UsuarioRespuestaDTO(u.getId(),u.getEmail(), u.getRol());
        return dto;
    }
}
