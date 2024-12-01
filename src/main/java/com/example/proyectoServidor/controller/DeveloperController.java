package com.example.proyectoServidor.controller;

import com.example.proyectoServidor.dto.GenericApiResponse;
import com.example.proyectoServidor.dto.DeveloperDto;
import com.example.proyectoServidor.model.Developer;
import com.example.proyectoServidor.service.DeveloperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/developers")
@Tag(name = "Desarrolladores", description = "Gestión de desarrolladores en la aplicación")
public class DeveloperController {

    private final DeveloperService developerService;

    @Autowired
    public DeveloperController(DeveloperService developerService) {
        this.developerService = developerService;
    }

    /**
     * Obtener todos los desarrolladores.
     * 
     * @return Lista de desarrolladores.
     * 
     *         He aladido a este controlador Las respuestas genericas
     *         ofrece una estructura más descriptiva y manejable para el cliente (y
     *         en caso de error tambien da más detalle).
     */
    @GetMapping
    @Operation(summary = "Obtener todos los desarrolladores", description = "Obtiene una lista de todos los desarrolladores.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de desarrolladores obtenida con éxito")
    })
    public ResponseEntity<GenericApiResponse<List<Developer>>> getAllDevelopers() {
        List<Developer> developers = developerService.findAllDevelopers();

        GenericApiResponse<List<Developer>> response = new GenericApiResponse<>(
                "Desarrolladores obtenidos con éxito",
                developers);

        return ResponseEntity.ok(response);
    }

    /**
     * Obtener un desarrollador por su ID.
     * 
     * @param id ID del desarrollador.
     * @return Desarrollador si existe.
     */
    @GetMapping("/{id}")
    @Operation(summary = "Obtener un desarrollador por ID", description = "Obtiene los detalles de un desarrollador por su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Desarrollador encontrado"),
            @ApiResponse(responseCode = "404", description = "Desarrollador no encontrado")
    })
    public ResponseEntity<GenericApiResponse<Developer>> getDeveloperById(
            @Parameter(description = "ID del desarrollador a obtener", example = "1") @PathVariable Integer id) {
        Optional<Developer> developer = developerService.findDeveloperById(id);

        if (developer.isPresent()) {
            GenericApiResponse<Developer> response = new GenericApiResponse<>(
                    "Desarrollador encontrado",
                    developer.get());
            return ResponseEntity.ok(response);
        } else {
            GenericApiResponse<Developer> response = new GenericApiResponse<>(
                    "Desarrollador no encontrado",
                    null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    /**
     * Crear un desarrollador.
     * 
     * @param developerDto Datos del desarrollador.
     * @return Desarrollador creado.
     */
    @PostMapping
    public ResponseEntity<GenericApiResponse<Developer>> createDeveloper(@RequestBody DeveloperDto developerDto) {
        try {
            Developer createdDeveloper = developerService.saveDeveloper(developerDto);

            // Crear la respuesta genérica
            GenericApiResponse<Developer> response = new GenericApiResponse<>(
                    "Desarrollador creado con éxito",
                    createdDeveloper);

            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new GenericApiResponse<>(e.getMessage(), null));
        }
    }

    /**
     * Eliminar un desarrollador por su ID.
     * 
     * @param id ID del desarrollador.
     * @return Respuesta vacía con el código de estado correspondiente.
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un desarrollador", description = "Elimina un desarrollador por su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Desarrollador eliminado con éxito"),
            @ApiResponse(responseCode = "404", description = "Desarrollador no encontrado")
    })
    public ResponseEntity<GenericApiResponse<Void>> deleteDeveloper(
            @Parameter(description = "ID del desarrollador a eliminar", example = "1") @PathVariable Integer id) {
        try {
            developerService.deleteDeveloper(id);

            GenericApiResponse<Void> response = new GenericApiResponse<>(
                    "Desarrollador eliminado con éxito",
                    null);

            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
        } catch (RuntimeException e) {
            GenericApiResponse<Void> response = new GenericApiResponse<>(
                    "Error al eliminar el desarrollador: " + e.getMessage(),
                    null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    // Nuevo endpoint añadido al controlador --> Añadir dev a un proyecto

    @PostMapping("/worked/{id}")
    public ResponseEntity<GenericApiResponse<Void>> setDeveloperWorked(
            @PathVariable Integer id,
            @RequestBody List<Integer> developerIds) {
        try {
            developerService.addDevelopersToProject(id, developerIds);
            GenericApiResponse<Void> response = new GenericApiResponse<>();
            response.setMessage("Desarrolladores añadidos al proyecto exitosamente.");
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            GenericApiResponse<Void> response = new GenericApiResponse<>();
            response.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }
    


}
