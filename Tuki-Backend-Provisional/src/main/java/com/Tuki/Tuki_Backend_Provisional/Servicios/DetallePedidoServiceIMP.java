package com.Tuki.Tuki_Backend_Provisional.Servicios;

import com.Tuki.Tuki_Backend_Provisional.Entidades.DetallePedido;
import com.Tuki.Tuki_Backend_Provisional.Entidades.Producto;
import com.Tuki.Tuki_Backend_Provisional.Repositorys.DetallePedidoRepository;
import com.Tuki.Tuki_Backend_Provisional.Repositorys.ProductoRepository;
import com.Tuki.Tuki_Backend_Provisional.Servicios.IntefacesServicios.DetallePedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class DetallePedidoServiceIMP implements DetallePedidoService {

    @Autowired
    ProductoRepository productoRepository;
    @Autowired
    DetallePedidoRepository detallePedidoRepository;

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
        productoRepository.save(producto);
        return detalle;
    }

    @Override
    public List<DetallePedido> buscarPorPedido(Long Idcategoria){
        return detallePedidoRepository.findByPedidoId(Idcategoria);
    }

    @Override
    public void recuperarStockYGuardar(DetallePedido detalle) {
        Producto producto = productoRepository.findById(detalle.getProducto().getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Producto no encontrado"));

        if (detalle.getProducto() == null) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "El producto no está cargado en el detalle");
        }

        producto.setStock(producto.getStock() + detalle.getCantidad());
        productoRepository.save(producto);
    }
}
