package com.example.proyectoServidor.service;

import com.example.proyectoServidor.dto.ProjectDto;
import com.example.proyectoServidor.model.Project;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ProjectService {
    Page<Project> getAllProjects(Pageable pageable);

    List<ProjectDto> getProjectsByNameContaining(String word);

    Optional<Project> getProjectById(Integer id);

    Project saveProject(ProjectDto projectDto); // Cambiar para aceptar ProjectDto

    Project updateProject(Integer id, ProjectDto projectDto);

    void deleteProject(Integer id);

    // endpoints para cambiar el estado de los proyectos

    void changeProjectStatusToTesting(Integer projectId);

    void changeProjectStatusToProduction(Integer projectId);

}
