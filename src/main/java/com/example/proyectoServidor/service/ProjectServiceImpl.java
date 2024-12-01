package com.example.proyectoServidor.service;

import com.example.proyectoServidor.dto.ProjectDto;
import com.example.proyectoServidor.dto.TechnologyDto;
import com.example.proyectoServidor.model.Project;
import com.example.proyectoServidor.model.Status;
import com.example.proyectoServidor.model.Technology;
import com.example.proyectoServidor.repository.ProjectRepository;
import com.example.proyectoServidor.repository.StatusRepository;
import com.example.proyectoServidor.repository.TechnologyRepository;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Implementación del servicio para gestionar proyectos.
 */
@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private StatusRepository statusRepository;

    @Autowired
    private TechnologyRepository technologyRepository;

    /**
     * Obtener todos los proyectos con soporte de paginación.
     * 
     * @param pageable Configuración de paginación.
     * @return Página de proyectos.
     */
    @Override
    public Page<Project> getAllProjects(Pageable pageable) {
        return projectRepository.findAll(pageable);
    }

    /**
     * Crear un proyecto.
     * 
     * @param projectDto DTO con los datos del proyecto.
     * @return Proyecto creado.
     */
    @Override
    @Transactional
    public Project saveProject(ProjectDto projectDto) {
        // Validar estado
        Status status = statusRepository.findById(projectDto.getStatus().getStatusId())
                .orElseThrow(() -> new IllegalArgumentException("Estado no encontrado"));

        // Validar tecnologías
        List<Technology> technologies = technologyRepository.findAllById(
                projectDto.getTechnologies().stream()
                        .map(TechnologyDto::getTechId)
                        .collect(Collectors.toList()));

        if (technologies.size() != projectDto.getTechnologies().size()) {
            throw new IllegalArgumentException("Una o más tecnologías no existen");
        }

        // Crear la entidad Project
        Project project = new Project();
        project.setProjectName(projectDto.getProjectName());
        project.setDescription(projectDto.getDescription());
        project.setStartDate(projectDto.getStartDate());
        project.setEndDate(projectDto.getEndDate());
        project.setRepositoryUrl(projectDto.getRepositoryUrl());
        project.setDemoUrl(projectDto.getDemoUrl());
        project.setPicture(projectDto.getPicture());
        project.setStatus(status);
        project.setTechnologies(technologies);

        // Guardar el proyecto
        return projectRepository.save(project);
    }

    /**
     * Buscar proyectos por palabra clave en su nombre.
     * 
     * @param word Palabra clave.
     * @return Lista de proyectos en formato DTO.
     */
    @Override
    @Transactional
    public List<ProjectDto> getProjectsByNameContaining(String word) {
        List<Project> projects = projectRepository.findByProjectNameContaining(word);
        return projects.stream()
                .map(this::castEntityToDto)
                .collect(Collectors.toList());
    }

    /**
     * Obtener un proyecto por su ID.
     * 
     * @param id ID del proyecto.
     * @return Proyecto si existe.
     */
    @Override
    public Optional<Project> getProjectById(Integer id) {
        return projectRepository.findById(id);
    }

    /**
     * Actualizar un proyecto existente.
     * 
     * @param id      ID del proyecto a actualizar.
     * @param project Datos actualizados del proyecto.
     * @return Proyecto actualizado.
     */
    @Override
    public Project updateProject(Integer id, ProjectDto projectDto) {
        return projectRepository.findById(id)
                .map(existingProject -> {
                    existingProject.setProjectName(projectDto.getProjectName());
                    existingProject.setDescription(projectDto.getDescription());
                    existingProject.setStartDate(projectDto.getStartDate());
                    existingProject.setEndDate(projectDto.getEndDate());
                    existingProject.setRepositoryUrl(projectDto.getRepositoryUrl());
                    existingProject.setDemoUrl(projectDto.getDemoUrl());
                    existingProject.setPicture(projectDto.getPicture());
                    existingProject.setStatus(statusRepository.findById(projectDto.getStatus().getStatusId())
                            .orElseThrow(() -> new IllegalArgumentException("Estado no encontrado")));
                    existingProject.setTechnologies(
                            technologyRepository.findAllById(
                                    projectDto.getTechnologies().stream()
                                            .map(TechnologyDto::getTechId)
                                            .collect(Collectors.toList())));
                    return projectRepository.save(existingProject);
                })
                .orElseThrow(() -> new RuntimeException("Project not found with id: " + id));
    }

    /**
     * Eliminar un proyecto por su ID.
     * 
     * @param id ID del proyecto a eliminar.
     */
    @Override
    public void deleteProject(Integer id) {
        if (projectRepository.existsById(id)) {
            projectRepository.deleteById(id);
        } else {
            throw new RuntimeException("Project not found with id: " + id);
        }
    }

    /**
     * Convertir una entidad Project a un DTO.
     * 
     * @param project Entidad Project.
     * @return DTO del proyecto.
     */
    private ProjectDto castEntityToDto(Project project) {
        ProjectDto projectDto = new ProjectDto();
        projectDto.setProjectId(project.getProjectId());
        projectDto.setProjectName(project.getProjectName());
        projectDto.setDescription(project.getDescription());
        projectDto.setStartDate(project.getStartDate());
        projectDto.setEndDate(project.getEndDate());
        projectDto.setRepositoryUrl(project.getRepositoryUrl());
        projectDto.setDemoUrl(project.getDemoUrl());
        projectDto.setPicture(project.getPicture());
        projectDto.setStatusName(project.getStatus().getStatusName());

        // Convertir tecnologías a TechnologyDto
        projectDto.setTechnologies(
                project.getTechnologies().stream()
                        .map(tech -> {
                            TechnologyDto dto = new TechnologyDto();
                            dto.setTechId(tech.getTechId());
                            dto.setTechName(tech.getTechName());
                            return dto;
                        })
                        .collect(Collectors.toList()));

        return projectDto;
    }

    /**
     * endpoints para cambiar el estado de los proyectos
     */

    @Override
    public void changeProjectStatusToTesting(Integer projectId) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new IllegalArgumentException("Proyecto no encontrado con ID: " + projectId));

        Status testingStatus = statusRepository.findById(2) // 2 es "Testing"
                .orElseThrow(() -> new IllegalArgumentException("Estado 'Testing' no encontrado"));

        project.setStatus(testingStatus);
        projectRepository.save(project);
    }

    @Override
    public void changeProjectStatusToProduction(Integer projectId) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new IllegalArgumentException("Proyecto no encontrado con ID: " + projectId));

        Status productionStatus = statusRepository.findById(3) // 3 es "Production"
                .orElseThrow(() -> new IllegalArgumentException("Estado 'Production' no encontrado"));

        project.setStatus(productionStatus);
        projectRepository.save(project);
    }


    // Implementamos el servicio de la customQuery(Convierto los proyectos en Dto's)
    @Override
    public List<ProjectDto> findProjectsByTechnology(String techName) {
        List<Project> projects = projectRepository.findByTechnologyName(techName);
        return projects.stream()
                .map(this::castEntityToDto)
                .collect(Collectors.toList());
    }

}
