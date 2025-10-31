package com.Tuki.Tuki_Backend_Provisional.Servicios;

import com.Tuki.Tuki_Backend_Provisional.Entidades.Usuario;
import com.Tuki.Tuki_Backend_Provisional.Entidades.UsuarioDTOs.UsuarioDTO;
import com.Tuki.Tuki_Backend_Provisional.Entidades.UsuarioDTOs.UsuarioPostDTO;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServiceIMP extends BaseServiceImpl<Usuario, Long, UsuarioPostDTO, UsuarioDTO> implements UsuarioService{


}
