package com.example.proyectoServidor.controller;

import com.example.proyectoServidor.dto.GenericApiResponse;
import com.example.proyectoServidor.dto.TechnologyDto;
import com.example.proyectoServidor.service.TechnologyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador para gestionar las tecnologías.
 */
@RestController
@RequestMapping("/api/v1/technologies")
public class TechnologyController {

    private final TechnologyService technologyService;

    @Autowired
    public TechnologyController(TechnologyService technologyService) {
        this.technologyService = technologyService;
    }

    /**
     * Obtener todas las tecnologías.
     * 
     * @return Lista de tecnologías.
     */
    @GetMapping
    public ResponseEntity<GenericApiResponse<List<TechnologyDto>>> getAllTechnologies() {
        List<TechnologyDto> technologies = technologyService.findAllTechnologies();

        GenericApiResponse<List<TechnologyDto>> response = new GenericApiResponse<>();
        response.setMessage("Tecnologías obtenidas con éxito");
        response.setContent(technologies);

        return ResponseEntity.ok(response);
    }

    /**
     * Obtener una tecnología por su ID.
     * 
     * @param id ID de la tecnología.
     * @return Tecnología si existe.
     */
    @GetMapping("/{id}")
    public ResponseEntity<GenericApiResponse<TechnologyDto>> getTechnologyById(@PathVariable Integer id) {
        return technologyService.findTechnologyById(id)
                .map(technology -> {
                    GenericApiResponse<TechnologyDto> response = new GenericApiResponse<>();
                    response.setMessage("Tecnología encontrada");
                    response.setContent(technology);
                    return ResponseEntity.ok(response);
                })
                .orElseGet(() -> {
                    GenericApiResponse<TechnologyDto> response = new GenericApiResponse<>();
                    response.setMessage("Tecnología no encontrada");
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
                });
    }

    /**
     * Crear una nueva tecnología.
     * 
     * @param technologyDto Datos de la tecnología.
     * @return Tecnología creada.
     */
    @PostMapping
    public ResponseEntity<GenericApiResponse<TechnologyDto>> createTechnology(
            @RequestBody TechnologyDto technologyDto) {
        TechnologyDto createdTechnology = technologyService.saveTechnology(technologyDto);

        GenericApiResponse<TechnologyDto> response = new GenericApiResponse<>();
        response.setMessage("Tecnología creada con éxito");
        response.setContent(createdTechnology);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    /**
     * Eliminar una tecnología por su ID.
     * 
     * @param id ID de la tecnología.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<GenericApiResponse<Void>> deleteTechnology(@PathVariable Integer id) {
        try {
            technologyService.deleteTechnology(id);

            GenericApiResponse<Void> response = new GenericApiResponse<>();
            response.setMessage("Tecnología eliminada con éxito");

            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
        } catch (RuntimeException e) {
            GenericApiResponse<Void> response = new GenericApiResponse<>();
            response.setMessage("Error al eliminar la tecnología: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    // Nuevo endpoint Para anyadir a un proyecto una tecnologia.

    @PostMapping("/used/{id}")
    public ResponseEntity<GenericApiResponse<Void>> setTechnologyUsed(
            @PathVariable Integer id,
            @RequestBody List<Integer> technologyIds) { // Cambiamos TechnologyDto por List<Integer>
        try {
            technologyService.addTechnologiesToProject(id, technologyIds); // Llamamos al método del servicio
            GenericApiResponse<Void> response = new GenericApiResponse<>();
            response.setMessage("Tecnologías añadidas al proyecto exitosamente.");
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            GenericApiResponse<Void> response = new GenericApiResponse<>();
            response.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

}
