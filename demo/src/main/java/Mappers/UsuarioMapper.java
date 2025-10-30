package Mappers;

import Entitys.DTOs.UsuarioDTO;
import Entitys.DTOs.UsuarioRespuestaDTO;
import Entitys.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    @Mapping(target = "rol", constant = "cliente")
    Usuario dtoToEntity(UsuarioDTO dto);

    UsuarioRespuestaDTO entityToRespuestaDTO(Usuario usuario);

    UsuarioDTO entityToDto(Usuario usuario); // si querés que MapStruct lo genere
}