package com.example.proyectoServidor.controller;

import com.example.proyectoServidor.model.Developer;
import com.example.proyectoServidor.service.DeveloperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://127.0.0.1:5500") // Añado CORS a los controladores 
@RequestMapping("/api/developers")
public class DeveloperController {

    private final DeveloperService developerService;

    @Autowired
    public DeveloperController(DeveloperService developerService) {
        this.developerService = developerService;
    }

    @GetMapping
    public List<Developer> getAllDevelopers() {
        return developerService.findAllDevelopers();
    }

    @GetMapping("/{id}")
    public Optional<Developer> getDeveloperById(@PathVariable Integer id) {
        return developerService.findDeveloperById(id);
    }

    @PostMapping
    public Developer createDeveloper(@RequestBody Developer developer) {
        return developerService.saveDeveloper(developer);
    }

    @DeleteMapping("/{id}")
    public void deleteDeveloper(@PathVariable Integer id) {
        developerService.deleteDeveloper(id);
    }
}
