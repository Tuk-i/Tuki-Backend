package com.Tuki.Tuki_Backend_Provisional.Servicios;

import com.Tuki.Tuki_Backend_Provisional.Entidades.Producto;
import com.Tuki.Tuki_Backend_Provisional.Entidades.UsuarioDTOs.Mappers.ProductoMapper;
import com.Tuki.Tuki_Backend_Provisional.Entidades.UsuarioDTOs.ProductoInputDTO;
import com.Tuki.Tuki_Backend_Provisional.Entidades.UsuarioDTOs.ProductoRespuestaDTO;
import com.Tuki.Tuki_Backend_Provisional.Repositorys.ProductoRepository;
import com.Tuki.Tuki_Backend_Provisional.Servicios.IntefacesServicios.ProductoService;
import org.springframework.stereotype.Service;

@Service
public class ProductoServiceIMP extends BaseServiceImpl<Producto, Long, ProductoInputDTO, ProductoRespuestaDTO> implements ProductoService {
    private final ProductoRepository productoRepository;
    private final ProductoMapper productoMapper;

    public ProductoServiceIMP(ProductoRepository productoRepository, ProductoMapper productoMapper) {
        super(productoRepository, productoMapper);
        this.productoRepository = productoRepository;
        this.productoMapper = productoMapper;
    }

}
