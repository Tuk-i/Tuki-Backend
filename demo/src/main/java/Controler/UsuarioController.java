package Controler;

import Entitys.Usuario;
import Services.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author leand
 */
@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService){
        this.usuarioService = usuarioService;
    }

    @GetMapping("/ping")
    public ResponseEntity<String> ping() {
        System.out.println(">>> LLEGÓ /api/usuarios/ping");
        return ResponseEntity.ok("ok");
    }

    @PostMapping("/login")
    public ResponseEntity<String> iniciarSesion(@RequestBody Usuario credenciales) {
        boolean esValido = usuarioService.verificarCredenciales(
                credenciales.getUsername(),
                credenciales.getPassword()
        );


        if (esValido){
            return ResponseEntity.ok("Inicio de sesión exitoso");
        } else {
            return ResponseEntity.status(401).body("Credenciales incorrectas");
        }

    }


    @PostMapping("/registro")
    public ResponseEntity<Usuario> registrar(@RequestBody Usuario nuevoUsuario){
        Usuario usuarioGuardado = usuarioService.createUsuario(nuevoUsuario);

        return ResponseEntity.status(201).body(usuarioGuardado);
    }

}
