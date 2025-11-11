package com.Tuki.Tuki_Backend_Provisional.Entidades.DTOs.ProductoDTOs;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProductoUpdateDTO(
        String nombre,
        String descripcion,
        Double precio,
        Integer stock,
        String urlImagen,
        Long categoriaId
){}
