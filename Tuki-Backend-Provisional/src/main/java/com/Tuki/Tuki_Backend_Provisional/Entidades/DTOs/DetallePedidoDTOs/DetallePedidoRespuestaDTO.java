package com.Tuki.Tuki_Backend_Provisional.Entidades.DTOs.DetallePedidoDTOs;

public record DetallePedidoRespuestaDTO(
        String nombreProducto,
        int cantidad,
        double subtotal
) {}
