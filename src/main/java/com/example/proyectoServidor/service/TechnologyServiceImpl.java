package com.example.proyectoServidor.service;

import com.example.proyectoServidor.model.Technology;
import com.example.proyectoServidor.repository.TechnologyRepository;
import com.example.proyectoServidor.service.TechnologyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TechnologyServiceImpl implements TechnologyService {

    private final TechnologyRepository technologyRepository;

    @Autowired
    public TechnologyServiceImpl(TechnologyRepository technologyRepository) {
        this.technologyRepository = technologyRepository;
    }

    @Override
    public List<Technology> findAllTechnologies() {
        return technologyRepository.findAll();
    }

    @Override
    public Optional<Technology> findTechnologyById(Integer techId) {
        return technologyRepository.findById(techId);
    }

    @Override
    public Technology saveTechnology(Technology technology) {
        return technologyRepository.save(technology);
    }

    @Override
    public void deleteTechnology(Integer techId) {
        technologyRepository.deleteById(techId);
    }
}
