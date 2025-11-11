package com.Tuki.Tuki_Backend_Provisional.Entidades.DTOs.Carrito;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record ItemCarritoDTO(
        @NotNull(message = "El ID del producto es obligatorio")
        Long productoId,

        @Min(value = 1, message = "La cantidad debe ser al menos 1")
        int cantidad
){}
