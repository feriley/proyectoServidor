package com.example.proyectoServidor.service;

import com.example.proyectoServidor.model.Developer;
import com.example.proyectoServidor.repository.DeveloperRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DeveloperServiceImpl implements DeveloperService {

    private final DeveloperRepository developerRepository;

    @Autowired
    public DeveloperServiceImpl(DeveloperRepository developerRepository) {
        this.developerRepository = developerRepository;
    }

    @Override
    public List<Developer> findAllDevelopers() {
        return developerRepository.findAll();
    }

    @Override
    public Optional<Developer> findDeveloperById(Integer developerId) {
        return developerRepository.findById(developerId);
    }

    @Override
    public Developer saveDeveloper(Developer developer) {
        if (developerRepository.existsByEmail(developer.getEmail())) {
            throw new RuntimeException("Email already exists");
        }
        return developerRepository.save(developer);
    }

    @Override
    public void deleteDeveloper(Integer developerId) {
        if (!developerRepository.existsById(developerId)) {
            throw new RuntimeException("Developer not found");
        }
        developerRepository.deleteById(developerId);
    }

    @Override
    public boolean existsByEmail(String email) {
        return developerRepository.existsByEmail(email);
    }
}
