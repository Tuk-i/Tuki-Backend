package com.Tuki.Tuki_Backend_Provisional.Entidades.UsuarioDTOs.Mappers;

import com.Tuki.Tuki_Backend_Provisional.Entidades.Producto;
import com.Tuki.Tuki_Backend_Provisional.Entidades.UsuarioDTOs.ProductoInputDTO;
import com.Tuki.Tuki_Backend_Provisional.Entidades.UsuarioDTOs.ProductoRespuestaDTO;
import org.springframework.stereotype.Component;

@Component
public class ProductoMapper implements BaseMapper<Producto, ProductoInputDTO, ProductoRespuestaDTO>{
    @Override
    public Producto dtoToEntity(ProductoInputDTO dtocreate) {
        return null;
    }

    @Override
    public ProductoRespuestaDTO entityToDTO(Producto entidad) {
        return null;
    }

    @Override
    public void actualizarEntidad(Producto entidad, ProductoInputDTO dto) {

    }
}
