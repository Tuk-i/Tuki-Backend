package com.Tuki.Tuki_Backend_Provisional.Entidades.UsuarioDTOs;

import com.Tuki.Tuki_Backend_Provisional.Entidades.Enum.Rol;

public record UsuarioDTO (String email, String password, Rol rol){

}