package com.example.proyectoServidor.service;

import com.example.proyectoServidor.dto.DeveloperDto;
import com.example.proyectoServidor.model.Developer;
import com.example.proyectoServidor.model.Project;
import com.example.proyectoServidor.repository.DeveloperRepository;
import com.example.proyectoServidor.repository.ProjectRepository;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Implementación de DeveloperService para gestionar la lógica de negocio
 * relacionada con los desarrolladores.
 */
@Service
public class DeveloperServiceImpl implements DeveloperService {

    private final DeveloperRepository developerRepository;
    private final ProjectRepository projectRepository;

    @Autowired
    public DeveloperServiceImpl(DeveloperRepository developerRepository, ProjectRepository projectRepository) {
        this.developerRepository = developerRepository;
        this.projectRepository = projectRepository;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Developer> findAllDevelopers() {
        return developerRepository.findAll();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Developer> findDeveloperById(Integer developerId) {
        return developerRepository.findById(developerId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Developer saveDeveloper(DeveloperDto developerDto) {
        if (developerRepository.existsByEmail(developerDto.getEmail())) {
            throw new RuntimeException("El email ya existe: " + developerDto.getEmail());
        }

        // Convertir DeveloperDto a Developer antes de guardar
        Developer developer = new Developer();
        developer.setDevName(developerDto.getDevName());
        developer.setDevSurname(developerDto.getDevSurname());
        developer.setEmail(developerDto.getEmail());
        developer.setLinkedinUrl(developerDto.getLinkedinUrl());
        developer.setGithubUrl(developerDto.getGithubUrl());

        return developerRepository.save(developer);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteDeveloper(Integer developerId) {
        if (!developerRepository.existsById(developerId)) {
            throw new RuntimeException("El desarrollador con ID " + developerId + " no existe.");
        }
        developerRepository.deleteById(developerId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean existsByEmail(String email) {
        return developerRepository.existsByEmail(email);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void addDevelopersToProject(Integer projectId, List<Integer> developerIds) {
        // Validar si el proyecto existe
        Project project = projectRepository.findById(projectId)
            .orElseThrow(() -> new RuntimeException("Proyecto no encontrado."));

        // Buscar los desarrolladores por sus IDs
        List<Developer> developers = developerRepository.findAllById(developerIds);

        // Validar si todos los desarrolladores existen
        if (developers.size() != developerIds.size()) {
            throw new RuntimeException("Uno o más desarrolladores no existen.");
        }

        // Agregar los desarrolladores al proyecto
        project.getDevelopers().addAll(developers);
        projectRepository.save(project);
    }
}
