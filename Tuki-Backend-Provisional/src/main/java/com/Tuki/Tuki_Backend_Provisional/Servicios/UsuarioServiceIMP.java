package com.Tuki.Tuki_Backend_Provisional.Servicios;

import com.Tuki.Tuki_Backend_Provisional.Entidades.Usuario;
import com.Tuki.Tuki_Backend_Provisional.Entidades.UsuarioDTOs.ErrorDTO;
import com.Tuki.Tuki_Backend_Provisional.Entidades.UsuarioDTOs.Mappers.UsuarioMapper;
import com.Tuki.Tuki_Backend_Provisional.Entidades.UsuarioDTOs.UsuarioRespuestaDTO;
import com.Tuki.Tuki_Backend_Provisional.Entidades.UsuarioDTOs.UsuarioInputDTO;
import com.Tuki.Tuki_Backend_Provisional.Repositorys.UsuarioRepository;
import com.Tuki.Tuki_Backend_Provisional.Servicios.IntefacesServicios.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioServiceIMP extends BaseServiceImpl<Usuario, Long, UsuarioInputDTO, UsuarioRespuestaDTO> implements UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;

    public UsuarioServiceIMP(UsuarioRepository usuarioRepository, UsuarioMapper usuarioMapper) {
        super(usuarioRepository, usuarioMapper);
        this.usuarioRepository = usuarioRepository;
        this.usuarioMapper = usuarioMapper;
    }



    @Override
    public ResponseEntity<?> registrar(UsuarioInputDTO dto){
        if (usuarioRepository.findByEmail(dto.email()).isPresent()){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorDTO("Email ya registrado"));
        }

        UsuarioRespuestaDTO creado = super.crear(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    @Override
    public ResponseEntity<?> login(UsuarioInputDTO dto){
        Optional<Usuario> usuarioOpt = usuarioRepository.findByEmail(dto.email());

        if (usuarioOpt.isEmpty() || !usuarioOpt.get().getPassword().equals(dto.password())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ErrorDTO("Credenciales inválidas"));
        }

        UsuarioRespuestaDTO dtoRespuesta = usuarioMapper.entityToDTO(usuarioOpt.get());
        return ResponseEntity.ok(dtoRespuesta);
    }

    @Override
    public ResponseEntity<?> editar(Long id,UsuarioInputDTO dto) {
        Optional<Usuario> existente = usuarioRepository.findByEmail(dto.email());
        if (existente.isPresent() && !existente.get().getId().equals(id)){
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body(new ErrorDTO("El Email ya esta en uso por otro usuario"));
        }

        UsuarioRespuestaDTO actualizado = super.actualizar(id,dto);
        return ResponseEntity.ok(actualizado);
    }


//    public ResponseEntity<?> login(UsuarioPostDTO dto){
//        return usuarioRepository.findByEmail(dto.email())
//                .filter(usuario -> usuario.getPassword().equals(dto.password()))
//                .map(usuario -> ResponseEntity.ok(usuarioMapper.entityToDTO(usuario)))
//                .orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED)
//                        .body(new ErrorDTO("Credenciales inválidas")));
//    }
}
