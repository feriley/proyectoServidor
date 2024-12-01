package com.example.proyectoServidor.controller;

import com.example.proyectoServidor.dto.GenericApiResponse;
import com.example.proyectoServidor.model.Status;
import com.example.proyectoServidor.service.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador para gestionar los estados (status) de los proyectos.
 */
@RestController
@RequestMapping("/api/v1/statuses")
public class StatusController {

    private final StatusService statusService;

    @Autowired
    public StatusController(StatusService statusService) {
        this.statusService = statusService;
    }

    /**
     * Obtener todos los estados.
     * 
     * @return Lista de estados.
     */
    @GetMapping
    public ResponseEntity<GenericApiResponse<List<Status>>> getAllStatuses() {
        List<Status> statuses = statusService.findAllStatuses();

        GenericApiResponse<List<Status>> response = new GenericApiResponse<>();
        response.setMessage("Estados obtenidos con éxito");
        response.setContent(statuses);

        return ResponseEntity.ok(response);
    }

    /**
     * Obtener un estado por su ID.
     * 
     * @param id ID del estado.
     * @return Estado si existe.
     */
    @GetMapping("/{id}")
    public ResponseEntity<GenericApiResponse<Status>> getStatusById(@PathVariable Integer id) {
        return statusService.findStatusById(id)
                .map(status -> {
                    GenericApiResponse<Status> response = new GenericApiResponse<>();
                    response.setMessage("Estado encontrado");
                    response.setContent(status);
                    return ResponseEntity.ok(response);
                })
                .orElseGet(() -> {
                    GenericApiResponse<Status> response = new GenericApiResponse<>();
                    response.setMessage("Estado no encontrado");
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
                });
    }

    /**
     * Crear un nuevo estado.
     * 
     * @param status Datos del estado.
     * @return Estado creado.
     */
    @PostMapping
    public ResponseEntity<GenericApiResponse<Status>> createStatus(@RequestBody Status status) {
        Status createdStatus = statusService.saveStatus(status);

        GenericApiResponse<Status> response = new GenericApiResponse<>();
        response.setMessage("Estado creado con éxito");
        response.setContent(createdStatus);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    /**
     * Eliminar un estado por su ID.
     * 
     * @param id ID del estado.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<GenericApiResponse<Void>> deleteStatus(@PathVariable Integer id) {
        try {
            statusService.deleteStatus(id);

            GenericApiResponse<Void> response = new GenericApiResponse<>();
            response.setMessage("Estado eliminado con éxito");

            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
        } catch (RuntimeException e) {
            GenericApiResponse<Void> response = new GenericApiResponse<>();
            response.setMessage("Error al eliminar el estado: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }
}
