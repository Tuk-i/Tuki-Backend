package com.Tuki.Tuki_Backend_Provisional.Entidades.Mappers;

import com.Tuki.Tuki_Backend_Provisional.Entidades.DTOs.DetallePedidoDTOs.DetallePedidoRespuestaDTO;
import com.Tuki.Tuki_Backend_Provisional.Entidades.DTOs.PedidoDTOs.PedidoRespuestaDTO;
import com.Tuki.Tuki_Backend_Provisional.Entidades.DTOs.PedidoDTOs.PedidoUpdateDTO;
import com.Tuki.Tuki_Backend_Provisional.Entidades.DetallePedido;
import com.Tuki.Tuki_Backend_Provisional.Entidades.Pedido;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PedidoMapper {

    public void actualizarEstado(Pedido pedido, PedidoUpdateDTO dto) {
        pedido.setEstado(dto.nuevoEstado());
    }


    public PedidoRespuestaDTO crearPedidoDTO(Pedido pedido){
        List<DetallePedidoRespuestaDTO> detalles = new ArrayList<>();
        for (DetallePedido detalle: pedido.getDetalles()){
            detalles.add(crearDetalleDTO(detalle));
        }

        return new PedidoRespuestaDTO(
                pedido.getId(),
                pedido.getFecha(),
                pedido.getEstado(),
                pedido.getTotal(),
                pedido.getUsuario().getNombre(),
                detalles
        );
    }


    public DetallePedidoRespuestaDTO crearDetalleDTO(DetallePedido detalle){
        return new DetallePedidoRespuestaDTO(detalle.getProducto().getNombre(), detalle.getCantidad(), detalle.getSubtotal());
    }
}
