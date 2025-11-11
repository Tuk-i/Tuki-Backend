package com.Tuki.Tuki_Backend_Provisional.Entidades.DTOs.PedidoDTOs;

import com.Tuki.Tuki_Backend_Provisional.Entidades.DTOs.DetallePedidoDTOs.DetallePedidoRespuestaDTO;
import com.Tuki.Tuki_Backend_Provisional.Entidades.Enum.Estado;

import java.time.LocalDate;
import java.util.List;

public record PedidoRespuestaDTO(
        Long id,
        LocalDate fecha,
        Estado estado,
        double total,
        String nombreUsuario,
        List<DetallePedidoRespuestaDTO> detalles
) {}
