package com.Tuki.Tuki_Backend_Provisional.Entidades.UsuarioDTOs.Mappers;

import com.Tuki.Tuki_Backend_Provisional.Entidades.Categoria;
import com.Tuki.Tuki_Backend_Provisional.Entidades.UsuarioDTOs.CategoriaInputDTO;
import com.Tuki.Tuki_Backend_Provisional.Entidades.UsuarioDTOs.CategoriaRespuestaDTO;

public class CategoriaMapper implements BaseMapper<Categoria, CategoriaInputDTO, CategoriaRespuestaDTO>{
    @Override
    public Categoria dtoToEntity(CategoriaInputDTO dtocreate) {
        return null;
    }

    @Override
    public CategoriaRespuestaDTO entityToDTO(Categoria entidad) {
        return null;
    }

    @Override
    public void actualizarEntidad(Categoria entidad, CategoriaInputDTO dto) {

    }
}
