package com.example.proyectoServidor.service;

import com.example.proyectoServidor.model.Status;
import java.util.List;
import java.util.Optional;

public interface StatusService {
    List<Status> findAllStatuses();
    Optional<Status> findStatusById(Integer statusId);
    Status saveStatus(Status status);
    void deleteStatus(Integer statusId);
}
