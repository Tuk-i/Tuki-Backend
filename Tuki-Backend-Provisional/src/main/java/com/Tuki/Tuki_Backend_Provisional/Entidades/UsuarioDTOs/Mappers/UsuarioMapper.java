package com.Tuki.Tuki_Backend_Provisional.Entidades.UsuarioDTOs.Mappers;

import com.Tuki.Tuki_Backend_Provisional.Entidades.Enum.Rol;
import com.Tuki.Tuki_Backend_Provisional.Entidades.UsuarioDTOs.UsuarioDTO;
import com.Tuki.Tuki_Backend_Provisional.Entidades.UsuarioDTOs.UsuarioPostDTO;
import com.Tuki.Tuki_Backend_Provisional.Entidades.Usuario;
import org.springframework.stereotype.Component;

@Component
public class UsuarioMapper implements BaseMapper<Usuario, UsuarioPostDTO, UsuarioDTO> {
    @Override
    public Usuario dtoToEntity(UsuarioPostDTO usuarioPostDTO) {
        Usuario usuario = new Usuario();
        usuario.setEmail(usuarioPostDTO.email());
        usuario.setPassword(usuarioPostDTO.password());
        usuario.setRol(Rol.CLIENTE);
        return usuario;
    }

    @Override
    public UsuarioDTO entityToDTO(Usuario entidad) {
        UsuarioDTO dto = new UsuarioDTO(entidad.getEmail(), entidad.getPassword(), entidad.getRol());
        return dto;
    }
}
