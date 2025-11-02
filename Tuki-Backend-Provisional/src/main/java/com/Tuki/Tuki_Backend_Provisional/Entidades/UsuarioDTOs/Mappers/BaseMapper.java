package com.Tuki.Tuki_Backend_Provisional.Entidades.UsuarioDTOs.Mappers;


public interface BaseMapper<E, DTOinput,DTOrespueta > {
    E dtoToEntity(DTOinput dtocreate);
    DTOrespueta entityToDTO(E entidad);
    void actualizarEntidad(E entidad, DTOinput dto);
}

