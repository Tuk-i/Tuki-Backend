package com.Tuki.Tuki_Backend_Provisional.CosasUtilesImportantes;

import com.Tuki.Tuki_Backend_Provisional.Entidades.DTOs.ErrorDTO;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

@RestControllerAdvice
public class GestionadorDeExepcionesREST {

//    @ExceptionHandler(ResponseStatusException.class)
//    public ResponseEntity<ErrorDTO> manejarResponseStatus(ResponseStatusException ex, HttpServletRequest request) {
//        ErrorDTO error = new ErrorDTO(
//                ex.getReason(),
//                ex.getStatusCode().value()
//        );
//        return ResponseEntity.status(ex.getStatusCode()).body(error);
//    }

//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity<ErrorDTO> manejarValidacion(MethodArgumentNotValidException ex) {
//        String mensaje = ex.getBindingResult().getFieldErrors().stream()
//                .map(error -> error.getField() + ": " + error.getDefaultMessage())
//                .findFirst()
//                .orElse("Error de validación");
//        return ResponseEntity.badRequest().body(new ErrorDTO(mensaje, 400));
//    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDTO> manejarValidacion(MethodArgumentNotValidException ex, HttpServletRequest request) {
        String mensaje = ex.getBindingResult().getFieldErrors().stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .findFirst()
                .orElse("Error de validación");

        ErrorDTO error = new ErrorDTO(mensaje, 400);
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorDTO> manejarEnumInvalido(HttpMessageNotReadableException ex, HttpServletRequest request) {
        String mensaje = "Valor inválido en el campo 'nuevoEstado'. Usá uno de: PENDIENTE, CANCELADO, TERMINADO, CONFIRMADO.";
        return ResponseEntity.badRequest().body(new ErrorDTO(mensaje, 400));
    }


}
