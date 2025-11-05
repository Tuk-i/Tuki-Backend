package com.Tuki.Tuki_Backend_Provisional.Entidades.DTOs.UsuarioDTOs;

import jakarta.validation.constraints.Email;

public record UsuarioUpdateDTO (
        @Email(message = "Formato de email inválido")
        String email,
        String password){
}
