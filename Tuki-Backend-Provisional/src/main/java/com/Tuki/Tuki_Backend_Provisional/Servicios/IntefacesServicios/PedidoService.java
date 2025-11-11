package com.Tuki.Tuki_Backend_Provisional.Servicios.IntefacesServicios;

import com.Tuki.Tuki_Backend_Provisional.Entidades.DTOs.Carrito.CarritoDTO;
import com.Tuki.Tuki_Backend_Provisional.Entidades.DTOs.PedidoDTOs.PedidoRespuestaDTO;
import com.Tuki.Tuki_Backend_Provisional.Entidades.DTOs.PedidoDTOs.PedidoUpdateDTO;
import com.Tuki.Tuki_Backend_Provisional.Entidades.Enum.Estado;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PedidoService {
    ResponseEntity<?> crearPedidoDesdeCarrito(CarritoDTO carritoDTO);
    ResponseEntity<?> actualizarEstadoPedido(PedidoUpdateDTO dto);
    List<PedidoRespuestaDTO> listarPedidosPorUsuario(Long usuarioId);
    List<PedidoRespuestaDTO> listarTodos();
    List<PedidoRespuestaDTO> listarPorEstado(Estado estado);
}
