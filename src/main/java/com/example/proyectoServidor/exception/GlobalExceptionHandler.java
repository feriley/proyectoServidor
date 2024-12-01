package com.example.proyectoServidor.exception;

import com.example.proyectoServidor.dto.GenericApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Apartado de RestController
 * Clase para manejar excepciones globales en la aplicación.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Maneja excepciones de tipo RuntimeException.
     * 
     * @param ex Excepción capturada.
     * @return Respuesta con un mensaje de error y un código de estado BAD_REQUEST.
     */
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<GenericApiResponse<Void>> handleRuntimeException(RuntimeException ex) {
        GenericApiResponse<Void> response = new GenericApiResponse<>();
        response.setMessage("Error: " + ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    /**
     * Maneja excepciones generales.
     * 
     * @param ex Excepción capturada.
     * @return Respuesta con un mensaje de error y un código de estado
     *         INTERNAL_SERVER_ERROR.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<GenericApiResponse<Void>> handleGeneralException(Exception ex) {
        GenericApiResponse<Void> response = new GenericApiResponse<>();
        response.setMessage("Error inesperado: " + ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
}
