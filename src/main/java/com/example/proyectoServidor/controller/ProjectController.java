package com.example.proyectoServidor.controller;

import com.example.proyectoServidor.model.Project;
import com.example.proyectoServidor.service.ProjectService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://127.0.0.1:5500") // Añado CORS a los controladores 
@RequestMapping("/api/projects")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    // 1) GET /projects -> Obtener todos los proyectos paginados
    @GetMapping
    public ResponseEntity<Page<Project>> getAllProjects(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Project> projects = projectService.getAllProjects(pageable);
        return ResponseEntity.ok(projects);
    }

    // 2) GET /projects/{word} -> Obtener proyectos que contengan la palabra "word" en su nombre
    @GetMapping("/{word}")
    public ResponseEntity<List<Project>> getProjectsByWord(@PathVariable("word") String word) {
        List<Project> projects = projectService.getProjectsByNameContaining(word);
        return ResponseEntity.ok(projects);
    }

    // 3) POST /projects -> Insertar un nuevo proyecto
    @PostMapping
    public ResponseEntity<Project> createProject(@RequestBody Project project) {
        // Validación básica
        if (project.getProjectName() == null || project.getProjectName().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        if (project.getDescription() == null || project.getDescription().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        // Crear el proyecto si la validación pasa
        Project createdProject = projectService.saveProject(project);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProject);
    }

    // 4) PUT /projects/{id} -> Editar un proyecto existente
    @PutMapping("/{id}")
    public ResponseEntity<Project> updateProject(
            @PathVariable("id") Integer id, @RequestBody Project project) {
        try {
            Project updatedProject = projectService.updateProject(id, project);
            return ResponseEntity.ok(updatedProject);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    

    // 5) DELETE /projects/{id} -> Eliminar un proyecto
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProject(@PathVariable("id") Integer id) {
        try {
            projectService.deleteProject(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
