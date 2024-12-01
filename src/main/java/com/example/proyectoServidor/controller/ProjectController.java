package com.example.proyectoServidor.controller;

import com.example.proyectoServidor.dto.GenericApiResponse;
import com.example.proyectoServidor.dto.ProjectDto;
import com.example.proyectoServidor.model.Project;
import com.example.proyectoServidor.service.ProjectService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

/**
 * Controlador para gestionar los endpoints relacionados con los proyectos.
 */
@RestController
@RequestMapping("/api/v1/projects")
@Tag(name = "Proyectos", description = "Gestión de proyectos en la aplicación")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @GetMapping
    @Operation(summary = "Obtener proyectos paginados", description = "Obtiene una lista de proyectos con soporte de paginación.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de proyectos obtenida con éxito")
    })
    public ResponseEntity<GenericApiResponse<Page<Project>>> getAllProjects(
            @Parameter(description = "Número de la página a obtener", example = "0") @RequestParam(value = "page", defaultValue = "0") int page,
            @Parameter(description = "Tamaño de la página a obtener", example = "10") @RequestParam(value = "size", defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Project> projects = projectService.getAllProjects(pageable);

        GenericApiResponse<Page<Project>> response = new GenericApiResponse<>();
        response.setMessage("Proyectos obtenidos con éxito");
        response.setContent(projects);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{word}")
    @Operation(summary = "Buscar proyectos por palabra clave", description = "Obtiene los proyectos que contienen una palabra clave en su nombre.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Proyectos encontrados")
    })
    public ResponseEntity<GenericApiResponse<List<ProjectDto>>> getProjectsByWord(
            @Parameter(description = "Palabra clave a buscar en los nombres de los proyectos", example = "API") @PathVariable("word") String word) {
        List<ProjectDto> projects = projectService.getProjectsByNameContaining(word);

        GenericApiResponse<List<ProjectDto>> response = new GenericApiResponse<>();
        response.setMessage("Proyectos encontrados con éxito");
        response.setContent(projects);

        return ResponseEntity.ok(response);
    }

    @PostMapping
    @Operation(summary = "Crear un nuevo proyecto", description = "Crea un nuevo proyecto en el sistema.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Proyecto creado con éxito"),
            @ApiResponse(responseCode = "400", description = "Datos enviados son inválidos")
    })
    public ResponseEntity<GenericApiResponse<Project>> createProject(@RequestBody ProjectDto projectDto) {
        GenericApiResponse<Project> response = new GenericApiResponse<>();
        try {
            Project createdProject = projectService.saveProject(projectDto);
            response.setMessage("Proyecto creado exitosamente");
            response.setContent(createdProject);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (IllegalArgumentException e) {
            response.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un proyecto", description = "Actualiza un proyecto existente.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Proyecto actualizado con éxito"),
            @ApiResponse(responseCode = "400", description = "Datos enviados son inválidos")
    })
    public ResponseEntity<GenericApiResponse<Project>> updateProject(
            @Parameter(description = "ID del proyecto a actualizar", example = "1") @PathVariable("id") Integer id,
            @RequestBody ProjectDto projectDto) {
        GenericApiResponse<Project> response = new GenericApiResponse<>();
        try {
            Project updatedProject = projectService.updateProject(id, projectDto);
            response.setMessage("Proyecto actualizado con éxito");
            response.setContent(updatedProject);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            response.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un proyecto", description = "Elimina un proyecto por su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Proyecto eliminado con éxito"),
            @ApiResponse(responseCode = "404", description = "Proyecto no encontrado")
    })
    public ResponseEntity<GenericApiResponse<Void>> deleteProject(
            @Parameter(description = "ID del proyecto a eliminar", example = "1") @PathVariable("id") Integer id) {
        GenericApiResponse<Void> response = new GenericApiResponse<>();
        try {
            projectService.deleteProject(id);
            response.setMessage("Proyecto eliminado con éxito");
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
        } catch (RuntimeException e) {
            response.setMessage("Proyecto no encontrado");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    /**
     * endpoints para cambiar el estado de los proyectos
     */

    @PatchMapping("/totesting/{id}")
    @Operation(summary = "Cambiar proyecto a Testing", description = "Cambia el estado del proyecto a 'Testing'.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Estado cambiado con éxito"),
            @ApiResponse(responseCode = "404", description = "Proyecto no encontrado")
    })
    public ResponseEntity<?> changeProjectToTesting(
            @Parameter(description = "ID del proyecto a actualizar", example = "1") @PathVariable Integer id) {
        try {
            projectService.changeProjectStatusToTesting(id);
            return ResponseEntity.ok("El proyecto se ha cambiado a estado 'Testing'.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PatchMapping("/toprod/{id}")
    @Operation(summary = "Cambiar proyecto a Production", description = "Cambia el estado del proyecto a 'Production'.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Estado cambiado con éxito"),
            @ApiResponse(responseCode = "404", description = "Proyecto no encontrado")
    })
    public ResponseEntity<?> changeProjectToProduction(
            @Parameter(description = "ID del proyecto a actualizar", example = "1") @PathVariable Integer id) {
        try {
            projectService.changeProjectStatusToProduction(id);
            return ResponseEntity.ok("El proyecto se ha cambiado a estado 'Production'.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/tec/{tech}")
    @Operation(summary = "Obtener proyectos por tecnología", description = "Busca proyectos asociados a una tecnología específica.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Proyectos encontrados con éxito"),
            @ApiResponse(responseCode = "404", description = "No se encontraron proyectos para la tecnología proporcionada")
    })
    public ResponseEntity<GenericApiResponse<List<ProjectDto>>> getProjectsByTechnology(
            @Parameter(description = "Nombre de la tecnología", example = "Java") @PathVariable("tech") String tech) {
        List<ProjectDto> projects = projectService.findProjectsByTechnology(tech);
        GenericApiResponse<List<ProjectDto>> response = new GenericApiResponse<>();
        response.setMessage("Proyectos asociados a la tecnología: " + tech);
        response.setContent(projects);
        return ResponseEntity.ok(response);
    }

}
