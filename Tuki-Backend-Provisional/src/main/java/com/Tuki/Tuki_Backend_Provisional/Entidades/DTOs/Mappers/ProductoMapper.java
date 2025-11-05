package com.Tuki.Tuki_Backend_Provisional.Entidades.DTOs.Mappers;

import com.Tuki.Tuki_Backend_Provisional.Entidades.DTOs.ProductoDTOs.ProductoUpdateDTO;
import com.Tuki.Tuki_Backend_Provisional.Entidades.Producto;
import com.Tuki.Tuki_Backend_Provisional.Entidades.DTOs.ProductoDTOs.ProductoPostDTO;
import com.Tuki.Tuki_Backend_Provisional.Entidades.DTOs.ProductoDTOs.ProductoRespuestaDTO;
import org.springframework.stereotype.Component;

@Component
public class ProductoMapper implements BaseMapper<Producto, ProductoPostDTO, ProductoUpdateDTO, ProductoRespuestaDTO>{
    @Override
    public Producto dtoToEntity(ProductoPostDTO dtocreate) {
        Producto producto = new Producto();
        producto.setNombre(dtocreate.nombre());
        producto.setDescripcion(dtocreate.descripcion());
        producto.setPrecio(dtocreate.precio());
        return producto;
    }

    @Override
    public ProductoRespuestaDTO entityToDTO(Producto producto) {
        return new ProductoRespuestaDTO(producto.getId(), producto.getNombre(), producto.getPrecio(),producto.getCategoria());
    }

    @Override
    public void actualizarEntidad(Producto producto, ProductoUpdateDTO dto) {
        if (dto.nombre() != null && !dto.nombre().isBlank() && !dto.nombre().equals(producto.getNombre())) {
            producto.setNombre(dto.nombre());
        }

        if (dto.descripcion() != null && !dto.descripcion().isBlank() && !dto.descripcion().equals(producto.getDescripcion())) {
            producto.setDescripcion(dto.descripcion());
        }

        if (dto.precio() != null && !dto.precio().equals(producto.getPrecio())) {
            producto.setPrecio(dto.precio());
        }

    }
}
