package com.example.proyectoServidor.dto;

import lombok.Data;

/**
 * DTO para representar los datos de un desarrollador.
 */
@Data
public class DeveloperDto {
    private Integer devId; // ID único del desarrollador
    private String devName; // Nombre del desarrollador
    private String devSurname; // Apellido del desarrollador
    private String email; // Correo electrónico del desarrollador
    private String linkedinUrl; // URL del perfil de LinkedIn
    private String githubUrl; // URL del perfil de GitHub
}
