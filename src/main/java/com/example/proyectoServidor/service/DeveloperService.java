package com.example.proyectoServidor.service;

import com.example.proyectoServidor.model.Developer;

import java.util.List;
import java.util.Optional;

public interface DeveloperService {
    List<Developer> findAllDevelopers();
    Optional<Developer> findDeveloperById(Integer developerId);
    Developer saveDeveloper(Developer developer);
    void deleteDeveloper(Integer developerId);
    boolean existsByEmail(String email); 
}
