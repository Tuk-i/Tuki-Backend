package com.example.Tuki.Mappers;

import com.example.Tuki.Entitys.DTOs.UsuarioDTO;
import com.example.Tuki.Entitys.DTOs.UsuarioRespuestaDTO;
import com.example.Tuki.Entitys.Usuario;
import com.example.Tuki.Entitys.Enum.Rol; // <-- import correcto del enum
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", imports = {Rol.class})
public interface UsuarioMapper {

    // setea siempre CLIENTE usando el import de arriba
    @Mapping(target = "rol", expression = "java(Rol.CLIENTE)")
    // si tu Usuario tiene email/username, mapealos acá:
    // @Mapping(target = "email", source = "correo")
    Usuario dtoToEntity(UsuarioDTO dto);

    UsuarioRespuestaDTO entityToRespuestaDTO(Usuario usuario);

    UsuarioDTO entityToDto(Usuario usuario);
}
