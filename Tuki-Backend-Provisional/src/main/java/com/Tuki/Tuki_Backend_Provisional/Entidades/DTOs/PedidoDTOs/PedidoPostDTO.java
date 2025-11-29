package com.Tuki.Tuki_Backend_Provisional.Entidades.DTOs.PedidoDTOs;

import jakarta.validation.constraints.NotNull;
import java.util.List;

public record PedidoPostDTO(
        @NotNull(message = "El ID del usuario es obligatorio")
        Long usuarioId,

        @NotNull(message = "Debe incluir al menos un producto")
        List<ItemCarritoDTO> items
){}
