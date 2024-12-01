package com.example.proyectoServidor.service;

import com.example.proyectoServidor.dto.DeveloperDto;
import com.example.proyectoServidor.model.Developer;

import java.util.List;
import java.util.Optional;

/**
 * Interfaz para gestionar los servicios relacionados con los desarrolladores.
 */
public interface DeveloperService {

    /**
     * Obtener todos los desarrolladores.
     * 
     * @return Lista de todos los desarrolladores.
     */
    List<Developer> findAllDevelopers();

    /**
     * Buscar un desarrollador por su ID.
     * 
     * @param developerId ID del desarrollador.
     * @return Objeto Optional con el desarrollador si existe.
     */
    Optional<Developer> findDeveloperById(Integer developerId);

    /**
     * Guardar un desarrollador desde un DeveloperDto.
     * 
     * @param developerDto Objeto DeveloperDto con los datos del desarrollador.
     * @return Objeto Developer guardado.
     */
    Developer saveDeveloper(DeveloperDto developerDto);

    /**
     * Eliminar un desarrollador por su ID.
     * 
     * @param developerId ID del desarrollador a eliminar.
     */
    void deleteDeveloper(Integer developerId);

    /**
     * Verificar si un desarrollador ya existe por su email.
     * 
     * @param email Email a verificar.
     * @return True si el email ya existe, false en caso contrario.
     */
    boolean existsByEmail(String email);

    /**
     * Anyadir a un projecto un Dev
     */

    void addDevelopersToProject(Integer projectId, List<Integer> developerIds);

}
