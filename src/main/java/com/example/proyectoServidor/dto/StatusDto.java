package com.example.proyectoServidor.dto;

import lombok.Data;

/**
 * DTO para representar el estado de un proyecto.
 */
@Data
public class StatusDto {
    private Integer statusId; // ID del estado (clave primaria en la base de datos)
}