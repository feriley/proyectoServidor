package com.example.proyectoServidor.service;

import com.example.proyectoServidor.dto.TechnologyDto;

import java.util.List;
import java.util.Optional;

/**
 * Interfaz para gestionar los servicios relacionados con las tecnologías.
 */
public interface TechnologyService {

    /**
     * Obtener todas las tecnologías.
     * 
     * @return Lista de tecnologías.
     */
    List<TechnologyDto> findAllTechnologies();

    /**
     * Buscar una tecnología por su ID.
     * 
     * @param techId ID de la tecnología.
     * @return Tecnología si existe.
     */
    Optional<TechnologyDto> findTechnologyById(Integer techId);

    /**
     * Guardar una nueva tecnología.
     * 
     * @param technologyDto Datos de la tecnología.
     * @return Tecnología guardada.
     */
    TechnologyDto saveTechnology(TechnologyDto technologyDto);

    /**
     * Eliminar una tecnología por su ID.
     * 
     * @param techId ID de la tecnología.
     */
    void deleteTechnology(Integer techId);

    /**
     * Anyadir a un proyecto una tech
     */

    void addTechnologiesToProject(Integer projectId, List<Integer> technologyIds);

}
