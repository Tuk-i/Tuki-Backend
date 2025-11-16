package com.Tuki.Tuki_Backend_Provisional.Entidades.DTOs.PedidoDTOs;

import com.Tuki.Tuki_Backend_Provisional.Entidades.Enum.Estado;
import jakarta.validation.constraints.NotNull;

public record PedidoUpdateDTO(
        @NotNull(message = "El ID del pedido es obligatorio")
        Long pedidoId,

        @NotNull(message = "El nuevo estado es obligatorio")
        Estado nuevoEstado
) {}