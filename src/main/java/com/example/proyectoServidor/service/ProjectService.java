package com.example.proyectoServidor.service;

import com.example.proyectoServidor.model.Project;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ProjectService {
    Page<Project> getAllProjects(Pageable pageable);  // Cambiar a Page<Project>
    List<Project> getProjectsByNameContaining(String word);
    Optional<Project> getProjectById(Integer id);
    Project saveProject(Project project);
    Project updateProject(Integer id, Project project);
    void deleteProject(Integer id);
}
