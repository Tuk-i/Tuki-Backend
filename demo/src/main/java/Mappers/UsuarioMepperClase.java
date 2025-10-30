package Mappers;

import Entitys.DTOs.UsuarioDTO;
import Entitys.DTOs.UsuarioRespuestaDTO;
import Entitys.Usuario;

public class UsuarioMepperClase {
    public static Usuario dtoToEntity(UsuarioDTO dto){
        Usuario usuario = new Usuario();
        usuario.setCorreo(dto.correo());
        usuario.setPassword(dto.password());
        usuario.setRol("cliente");
        return usuario;
    }

    public static UsuarioRespuestaDTO entityToRespuestaDTO(Usuario u){
        UsuarioRespuestaDTO dto = new UsuarioRespuestaDTO(u.getId(),u.getCorreo(), u.getRol());
        return dto;
    }
}
