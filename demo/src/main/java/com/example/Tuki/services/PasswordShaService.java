package com.example.Tuki.services;

import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Base64;

@Service
public class PasswordShaService {

    private static final int SALT_BYTES = 16;
    private static final int DEFAULT_ITERATIONS = 100_000; // subí/bajá según tu equipo
    private static final SecureRandom RNG = new SecureRandom();

    public String hash(String rawPassword) {
        byte[] salt = new byte[SALT_BYTES];
        RNG.nextBytes(salt);
        byte[] derived = deriveSha256(rawPassword, salt, DEFAULT_ITERATIONS);
        String saltB64 = Base64.getEncoder().encodeToString(salt);
        String hashB64 = Base64.getEncoder().encodeToString(derived);
        return DEFAULT_ITERATIONS + ":" + saltB64 + ":" + hashB64;
    }

    public boolean matches(String rawPassword, String stored) {
        // esperado: iterations:salt:hash (Base64)
        String[] parts = stored.split(":");
        if (parts.length != 3) return false;

        int iterations;
        try {
            iterations = Integer.parseInt(parts[0]);
        } catch (NumberFormatException e) {
            return false;
        }

        byte[] salt = Base64.getDecoder().decode(parts[1]);
        byte[] expected = Base64.getDecoder().decode(parts[2]);

        byte[] actual = deriveSha256(rawPassword, salt, iterations);
        return constantTimeEquals(expected, actual);
    }

    private byte[] deriveSha256(String rawPassword, byte[] salt, int iterations) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            // primera pasada: hash(salt || password)
            md.update(salt);
            byte[] result = md.digest(rawPassword.getBytes(StandardCharsets.UTF_8));
            // iteraciones restantes: hash(salt || hashPrevio)
            for (int i = 1; i < iterations; i++) {
                md.reset();
                md.update(salt);
                result = md.digest(result);
            }
            return result;
        } catch (Exception e) {
            throw new IllegalStateException("SHA-256 no disponible", e);
        }
    }

    private boolean constantTimeEquals(byte[] a, byte[] b) {
        if (a.length != b.length) return false;
        int r = 0;
        for (int i = 0; i < a.length; i++) {
            r |= a[i] ^ b[i];
        }
        return r == 0;
    }
}