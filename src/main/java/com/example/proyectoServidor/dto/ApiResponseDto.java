package com.example.proyectoServidor.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO genérico para las respuestas de la API.
 * 
 * @param <T> Tipo de los datos que se devuelven en la respuesta.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponseDto<T> {
    private String message; // Mensaje descriptivo
    private T data;         // Datos devueltos
}
