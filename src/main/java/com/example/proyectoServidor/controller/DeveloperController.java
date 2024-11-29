package com.example.proyectoServidor.controller;

import com.example.proyectoServidor.model.Developer;
import com.example.proyectoServidor.service.DeveloperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://127.0.0.1:5500")
@RequestMapping("/api/v1/developers")
public class DeveloperController {

    private final DeveloperService developerService;

    @Autowired
    public DeveloperController(DeveloperService developerService) {
        this.developerService = developerService;
    }

    // GET /developers -> Obtener todos los desarrolladores
    @GetMapping
    public ResponseEntity<List<Developer>> getAllDevelopers() {
        List<Developer> developers = developerService.findAllDevelopers();
        return ResponseEntity.ok(developers);
    }

    // GET /developers/{id} -> Obtener desarrollador por ID
    @GetMapping("/{id}")
    public ResponseEntity<Developer> getDeveloperById(@PathVariable Integer id) {
        Optional<Developer> developer = developerService.findDeveloperById(id);
        return developer.map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // POST /developers -> Insertar un programador
    @PostMapping
    public ResponseEntity<Developer> createDeveloper(@RequestBody Developer developer) {
        try {
            Developer createdDeveloper = developerService.saveDeveloper(developer);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdDeveloper);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    // DELETE /developers/{id} -> Borrar un programador
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDeveloper(@PathVariable Integer id) {
        try {
            developerService.deleteDeveloper(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
