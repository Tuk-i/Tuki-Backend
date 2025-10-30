package Services;

import Entitys.Usuario;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

@Service
public class JwtService {
    // 🔐 Clave secreta y duración del token, inyectadas desde application.properties
    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private long expiration;

    // 🔑 Convierte la clave secreta en un objeto Key válido para firmar
    private Key getKey() {
        return Keys.hmacShaKeyFor(secret.getBytes());
    }

    // 🧾 Genera el token JWT con claims personalizados
    public String generarToken(Usuario usuario) {
        return Jwts.builder()
                .setSubject(usuario.getCorreo()) // identificador principal
                .claim("id", usuario.getId())    // datos adicionales
                .claim("rol", usuario.getRol())
                .setIssuedAt(new Date())         // fecha de emisión
                .setExpiration(new Date(System.currentTimeMillis() + expiration)) // expiración
                .signWith(getKey(), SignatureAlgorithm.HS256) // firma con clave secreta
                .compact();
    }

    // ✅ Valida si el token es correcto y no expiró
    public boolean validarToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(getKey())
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (JwtException e) {
            return false;
        }
    }

    // 🔍 Extrae los datos del token (claims) para usarlos en lógica de negocio
    public Claims extraerClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
