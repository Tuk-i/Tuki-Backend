package com.Tuki.Tuki_Backend_Provisional.Entidades.DTOs.Mappers;

import com.Tuki.Tuki_Backend_Provisional.Entidades.Enum.Rol;
import com.Tuki.Tuki_Backend_Provisional.Entidades.DTOs.UsuarioDTOs.UsuarioRespuestaDTO;
import com.Tuki.Tuki_Backend_Provisional.Entidades.DTOs.UsuarioDTOs.UsuarioPostDTO;
import com.Tuki.Tuki_Backend_Provisional.Entidades.Usuario;
import com.Tuki.Tuki_Backend_Provisional.Entidades.DTOs.UsuarioDTOs.UsuarioUpdateDTO;
import org.springframework.stereotype.Component;

@Component
public class UsuarioMapper implements BaseMapper<Usuario, UsuarioPostDTO, UsuarioUpdateDTO, UsuarioRespuestaDTO> {
    @Override
    public Usuario dtoToEntity(UsuarioPostDTO usuarioPostDTO) {
        Usuario usuario = new Usuario();
        usuario.setEmail(usuarioPostDTO.email());
        usuario.setPassword(usuarioPostDTO.password());
        usuario.setRol(Rol.CLIENTE);
        return usuario;
    }

    @Override
    public UsuarioRespuestaDTO entityToDTO(Usuario entidad) {
        return new UsuarioRespuestaDTO(entidad.getId(),entidad.getEmail(), entidad.getRol());
    }

    @Override
    public void actualizarEntidad(Usuario usuario, UsuarioUpdateDTO dto) {

        if (dto.email() != null && !dto.email().isBlank() && !dto.email().equals(usuario.getEmail())){
            usuario.setEmail(dto.email());
        }

        if (dto.password() != null && !dto.password().isBlank() && !dto.password().equals(usuario.getPassword())){
            usuario.setPassword(dto.password());
        }
    }
}
