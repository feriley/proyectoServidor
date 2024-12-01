package com.example.proyectoServidor.service;

import com.example.proyectoServidor.dto.TechnologyDto;
import com.example.proyectoServidor.model.Project;
import com.example.proyectoServidor.model.Technology;
import com.example.proyectoServidor.repository.ProjectRepository;
import com.example.proyectoServidor.repository.TechnologyRepository;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Implementación de los servicios relacionados con las tecnologías.
 */
@Service
public class TechnologyServiceImpl implements TechnologyService {

    private final TechnologyRepository technologyRepository;
    private final ProjectRepository projectRepository;

    @Autowired
    public TechnologyServiceImpl(TechnologyRepository technologyRepository, ProjectRepository projectRepository) {
        this.technologyRepository = technologyRepository;
        this.projectRepository = projectRepository;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<TechnologyDto> findAllTechnologies() {
        return technologyRepository.findAll()
                .stream()
                .map(this::castEntityToDto)
                .collect(Collectors.toList());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<TechnologyDto> findTechnologyById(Integer techId) {
        return technologyRepository.findById(techId)
                .map(this::castEntityToDto);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TechnologyDto saveTechnology(TechnologyDto technologyDto) {
        Technology technology = new Technology();
        technology.setTechId(technologyDto.getTechId());
        technology.setTechName(technologyDto.getTechName());

        Technology savedTechnology = technologyRepository.save(technology);
        return castEntityToDto(savedTechnology);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteTechnology(Integer techId) {
        if (!technologyRepository.existsById(techId)) {
            throw new RuntimeException("La tecnología con ID " + techId + " no existe.");
        }
        technologyRepository.deleteById(techId);
    }

    /**
     * Convertir una entidad Technology a su DTO correspondiente.
     * 
     * @param technology Entidad Technology.
     * @return DTO de Technology.
     */
    private TechnologyDto castEntityToDto(Technology technology) {
        TechnologyDto dto = new TechnologyDto();
        dto.setTechId(technology.getTechId());
        dto.setTechName(technology.getTechName());
        return dto;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void addTechnologiesToProject(Integer projectId, List<Integer> technologyIds) {
        // Validar si el proyecto existe
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Proyecto no encontrado."));

        // Buscar las tecnologías por sus IDs
        List<Technology> technologies = technologyRepository.findAllById(technologyIds);

        // Validar si todas las tecnologías existen
        if (technologies.size() != technologyIds.size()) {
            throw new RuntimeException("Una o más tecnologías no existen.");
        }

        // Agregar las tecnologías al proyecto
        project.getTechnologies().addAll(technologies);
        projectRepository.save(project);
    }

}
