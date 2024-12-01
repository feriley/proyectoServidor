package com.example.proyectoServidor.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

/**
 * DTO para representar un proyecto con sus relaciones.
 */
@Data
public class ProjectDto {
    private Integer projectId; // Opcional para la creación, obligatorio para la edición
    private String projectName; // Nombre del proyecto
    private String description; // Descripción del proyecto
    private LocalDate startDate; // Fecha de inicio
    private LocalDate endDate; // Fecha de finalización
    private String repositoryUrl; // URL del repositorio
    private String demoUrl; // URL de la demo
    private String picture; // URL de la imagen

    // Relación con el estado del proyecto
    private StatusDto status;

    // Lista de tecnologías asociadas
    private List<TechnologyDto> technologies;

    // Lista de desarrolladores (nombres completos)
    private List<String> developers;

    // Nombre del estado (cuando se transforma una entidad a DTO)
    private String statusName;
}