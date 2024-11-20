package com.example.proyectoServidor.service;

import com.example.proyectoServidor.model.Status;
import com.example.proyectoServidor.repository.StatusRepository;
import com.example.proyectoServidor.service.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StatusServiceImpl implements StatusService {

    private final StatusRepository statusRepository;

    @Autowired
    public StatusServiceImpl(StatusRepository statusRepository) {
        this.statusRepository = statusRepository;
    }

    @Override
    public List<Status> findAllStatuses() {
        return statusRepository.findAll();
    }

    @Override
    public Optional<Status> findStatusById(Integer statusId) {
        return statusRepository.findById(statusId);
    }

    @Override
    public Status saveStatus(Status status) {
        return statusRepository.save(status);
    }

    @Override
    public void deleteStatus(Integer statusId) {
        statusRepository.deleteById(statusId);
    }
}
