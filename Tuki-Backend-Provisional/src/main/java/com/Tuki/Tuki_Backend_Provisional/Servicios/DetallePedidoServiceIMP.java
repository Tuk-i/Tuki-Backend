package com.Tuki.Tuki_Backend_Provisional.Servicios;

import com.Tuki.Tuki_Backend_Provisional.Entidades.DetallePedido;
import com.Tuki.Tuki_Backend_Provisional.Entidades.Producto;
import com.Tuki.Tuki_Backend_Provisional.Repositorys.ProductoRepository;
import com.Tuki.Tuki_Backend_Provisional.Servicios.IntefacesServicios.DetallePedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class DetallePedidoServiceIMP implements DetallePedidoService {

    @Autowired
    ProductoRepository productoRepository;

    @Override
    public DetallePedido crearDetalle(Long productoId, int cantidad) {
        Producto producto = productoRepository.findById(productoId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Producto no encontrado"));

        if (producto.getStock() < cantidad){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Stock insuficiente para " + producto.getNombre());
        }
        DetallePedido detalle = new DetallePedido();
        detalle.setProducto(producto);
        detalle.setCantidad(cantidad);
        detalle.calcularSubtotal();
        return detalle;
    }
}
