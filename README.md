# ✅ Recomendación adicional: JWT

Si estás usando `JwtService`, asegurate de incluir también:
```
# 🔐 Clave secreta para firmar el token (mínimo 32 caracteres)
jwt.secret=mi_clave_super_secreta_12345678901234567890

# ⏳ Duración del token en milisegundos (1 hora)
jwt.expiration=3600000

```

esto en el archivo application.properties

Investigar para que se usa Jwt
como crear un token y como usar la clave y la expiración