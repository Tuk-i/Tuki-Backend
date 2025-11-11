package com.Tuki.Tuki_Backend_Provisional.Servicios.IntefacesServicios;

import com.Tuki.Tuki_Backend_Provisional.Entidades.DetallePedido;

import java.util.List;

public interface DetallePedidoService {
    DetallePedido crearDetalle(Long productoId, int cantidad);
    List<DetallePedido> buscarPorPedido(Long Idcategoria);
    void recuperarStockYGuardar(DetallePedido detalle);
}
