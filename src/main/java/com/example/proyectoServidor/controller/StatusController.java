package com.example.proyectoServidor.controller;

import com.example.proyectoServidor.model.Status;
import com.example.proyectoServidor.service.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/statuses")
@CrossOrigin(origins = "http://127.0.0.1:5500") // Añado CORS a los controladores
public class StatusController {

    private final StatusService statusService;

    @Autowired
    public StatusController(StatusService statusService) {
        this.statusService = statusService;
    }

    @GetMapping
    public List<Status> getAllStatuses() {
        return statusService.findAllStatuses();
    }

    @GetMapping("/{id}")
    public Optional<Status> getStatusById(@PathVariable Integer id) {
        return statusService.findStatusById(id);
    }

    @PostMapping
    public Status createStatus(@RequestBody Status status) {
        return statusService.saveStatus(status);
    }

    @DeleteMapping("/{id}")
    public void deleteStatus(@PathVariable Integer id) {
        statusService.deleteStatus(id);
    }
}
