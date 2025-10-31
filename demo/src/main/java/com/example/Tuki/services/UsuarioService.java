package com.example.Tuki.services;

import com.example.Tuki.Entitys.Usuario;
import com.example.Tuki.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 *
 * @author leand
 */
@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final PasswordShaService passwordService;

    public UsuarioService(UsuarioRepository usuarioRepository, PasswordShaService passwordService) {
        this.usuarioRepository = usuarioRepository;
        this.passwordService = passwordService;
    }

    public boolean verificarCredenciales(String username, String password){
        return usuarioRepository.findByUsername(username)
                .map(usuario -> passwordService.matches(password, usuario.getPassword()))
                .orElse(false);
    }

    @Transactional
    public Usuario createUsuario(Usuario usuario) {
        if (usuarioRepository.existsByUsername(usuario.getUsername())) {
            throw new RuntimeException("Usuario existente");
        }
        usuario.setPassword(passwordService.hash(usuario.getPassword()));
        return usuarioRepository.save(usuario);
    }

    public Optional<Usuario> buscarPorUsername(String username) {
        return usuarioRepository.findByUsername(username);
    }



}
