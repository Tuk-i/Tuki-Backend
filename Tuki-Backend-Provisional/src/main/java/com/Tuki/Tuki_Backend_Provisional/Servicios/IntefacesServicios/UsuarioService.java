package com.Tuki.Tuki_Backend_Provisional.Servicios.IntefacesServicios;

import com.Tuki.Tuki_Backend_Provisional.Entidades.Usuario;
import com.Tuki.Tuki_Backend_Provisional.Entidades.UsuarioDTOs.UsuarioRespuestaDTO;
import com.Tuki.Tuki_Backend_Provisional.Entidades.UsuarioDTOs.UsuarioInputDTO;
import org.springframework.http.ResponseEntity;


public interface UsuarioService extends BaseService<Usuario, Long, UsuarioInputDTO, UsuarioRespuestaDTO> {
    //metodo especifico de usuario //

    ResponseEntity<?> registrar(UsuarioInputDTO dto);
    ResponseEntity<?> login(UsuarioInputDTO dto);
    ResponseEntity<?> editar(Long id, UsuarioInputDTO dto);

}
