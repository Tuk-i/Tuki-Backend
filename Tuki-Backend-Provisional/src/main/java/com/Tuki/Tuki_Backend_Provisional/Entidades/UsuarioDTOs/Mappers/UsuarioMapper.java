package com.Tuki.Tuki_Backend_Provisional.Entidades.UsuarioDTOs.Mappers;

import com.Tuki.Tuki_Backend_Provisional.Entidades.Enum.Rol;
import com.Tuki.Tuki_Backend_Provisional.Entidades.UsuarioDTOs.UsuarioRespuestaDTO;
import com.Tuki.Tuki_Backend_Provisional.Entidades.UsuarioDTOs.UsuarioInputDTO;
import com.Tuki.Tuki_Backend_Provisional.Entidades.Usuario;
import org.springframework.stereotype.Component;

@Component
public class UsuarioMapper implements BaseMapper<Usuario, UsuarioInputDTO, UsuarioRespuestaDTO> {
    @Override
    public Usuario dtoToEntity(UsuarioInputDTO usuarioPostDTO) {
        Usuario usuario = new Usuario();
        usuario.setEmail(usuarioPostDTO.email());
        usuario.setPassword(usuarioPostDTO.password());
        usuario.setRol(Rol.CLIENTE);
        return usuario;
    }

    @Override
    public UsuarioRespuestaDTO entityToDTO(Usuario entidad) {
        UsuarioRespuestaDTO dto = new UsuarioRespuestaDTO(entidad.getId(),entidad.getEmail(), entidad.getRol());
        return dto;
    }

    @Override
    public void actualizarEntidad(Usuario usuario, UsuarioInputDTO dto) {
        usuario.setEmail(dto.email());
        usuario.setPassword(dto.password());
    }


}
