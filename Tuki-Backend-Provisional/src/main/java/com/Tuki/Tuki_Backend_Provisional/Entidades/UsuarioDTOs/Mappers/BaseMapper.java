package com.Tuki.Tuki_Backend_Provisional.Entidades.UsuarioDTOs.Mappers;


public interface BaseMapper<E, DTOcreate,DTOrespueta > {
    E dtoToEntity(DTOcreate dtocreate);
    DTOrespueta entityToDTO(E entidad);
}
