package com.example.proyectoServidor.service;

import com.example.proyectoServidor.model.Project;
import com.example.proyectoServidor.repository.ProjectRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    // Obtener todos los proyectos con paginación
    @Override
    public Page<Project> getAllProjects(Pageable pageable) {
        return projectRepository.findAll(pageable);
    }

    // Obtener proyectos por palabra en el nombre
    @Override
    public List<Project> getProjectsByNameContaining(String word) {
        return projectRepository.findByProjectNameContaining(word);
    }

    // Obtener un proyecto por ID
    @Override
    public Optional<Project> getProjectById(Integer id) {
        return projectRepository.findById(id);
    }

    // Crear un nuevo proyecto
    @Override
    public Project saveProject(Project project) {
        return projectRepository.save(project);
    }

    // Actualizar un proyecto existente
    @Override
    public Project updateProject(Integer id, Project project) {
        return projectRepository.findById(id)
            .map(existingProject -> {
                existingProject.setProjectName(project.getProjectName());
                existingProject.setDescription(project.getDescription());
                existingProject.setStartDate(project.getStartDate());
                existingProject.setEndDate(project.getEndDate());
                existingProject.setRepositoryUrl(project.getRepositoryUrl());
                existingProject.setDemoUrl(project.getDemoUrl());
                existingProject.setPicture(project.getPicture());
                existingProject.setStatus(project.getStatus());
                existingProject.setTechnologies(project.getTechnologies());
                existingProject.setDevelopers(project.getDevelopers());
                return projectRepository.save(existingProject);
            })
            .orElseThrow(() -> new RuntimeException("Project not found with id: " + id));  // Lanzamos una excepción si no se encuentra el proyecto
    }

    // Eliminar un proyecto
    @Override
    public void deleteProject(Integer id) {
        if (projectRepository.existsById(id)) {
            projectRepository.deleteById(id);
        } else {
            throw new RuntimeException("Project not found with id: " + id);
        }
    }
}
