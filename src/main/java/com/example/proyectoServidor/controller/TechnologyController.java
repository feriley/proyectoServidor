package com.example.proyectoServidor.controller;

import com.example.proyectoServidor.model.Technology;
import com.example.proyectoServidor.service.TechnologyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://127.0.0.1:5500") // Añado CORS a los controladores
@RequestMapping("/api/technologies")
public class TechnologyController {

    private final TechnologyService technologyService;

    @Autowired
    public TechnologyController(TechnologyService technologyService) {
        this.technologyService = technologyService;
    }

    @GetMapping
    public List<Technology> getAllTechnologies() {
        return technologyService.findAllTechnologies();
    }

    @GetMapping("/{id}")
    public Optional<Technology> getTechnologyById(@PathVariable Integer id) {
        return technologyService.findTechnologyById(id);
    }

    @PostMapping
    public Technology createTechnology(@RequestBody Technology technology) {
        return technologyService.saveTechnology(technology);
    }

    @DeleteMapping("/{id}")
    public void deleteTechnology(@PathVariable Integer id) {
        technologyService.deleteTechnology(id);
    }
}
