package com.Tuki.Tuki_Backend_Provisional.Servicios.IntefacesServicios;

import com.Tuki.Tuki_Backend_Provisional.Entidades.DetallePedido;

public interface DetallePedidoService {
    DetallePedido crearDetalle(Long productoId, int cantidad);
}
