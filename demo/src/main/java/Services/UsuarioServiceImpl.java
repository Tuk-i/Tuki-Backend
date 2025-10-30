package Services;

import Entitys.DTOs.UsuarioDTO;
import Entitys.DTOs.UsuarioRespuestaDTO;
import Entitys.DTOs.UsuarioSesionDTO;
import Entitys.Usuario;
import Mappers.UsuarioMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioServiceImpl implements UsuarioService{

    @Autowired
    UsuarioRepository usuarioRepository;
    @Autowired
    private UsuarioMapper usuarioMapper;
    @Autowired
    private JwtService jwtService;

    @Override
    public List<UsuarioDTO> listarTodos() {
        return List.of();
    }

    @Override
    public UsuarioDTO busacarPorId(Long id) {
        return null;
    }

    @Override
    public UsuarioSesionDTO guardar(UsuarioDTO dto) {

        Usuario usuario = usuarioMapper.dtoToEntity(dto);
        usuarioRepository.save(usuario);
        UsuarioRespuestaDTO usuarioRespuestaDTO = usuarioMapper.entityToRespuestaDTO(usuario);
        String token = jwtService.generarToken(usuario);
        return new UsuarioSesionDTO(usuarioRespuestaDTO,token);
    }

    @Override
    public UsuarioDTO actualizar(Long id, UsuarioDTO dto) {
        return null;
    }

    @Override
    public void eliminar(Long id) {

    }
}
