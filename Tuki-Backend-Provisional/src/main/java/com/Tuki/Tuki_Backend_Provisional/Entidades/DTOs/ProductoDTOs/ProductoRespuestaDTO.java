package com.Tuki.Tuki_Backend_Provisional.Entidades.DTOs.ProductoDTOs;

import com.Tuki.Tuki_Backend_Provisional.Entidades.Categoria;

public record ProductoRespuestaDTO (
        Long id,
        String nombre,
        double precio,
        Integer stock,
        String urlImagen,
        String categoria
){}
