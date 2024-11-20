package com.example.proyectoServidor.service;

import com.example.proyectoServidor.model.Technology;
import java.util.List;
import java.util.Optional;

public interface TechnologyService {
    List<Technology> findAllTechnologies();
    Optional<Technology> findTechnologyById(Integer techId);
    Technology saveTechnology(Technology technology);
    void deleteTechnology(Integer techId);
}
