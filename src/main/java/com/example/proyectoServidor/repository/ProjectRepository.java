package com.example.proyectoServidor.repository;

import com.example.proyectoServidor.model.Project;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Integer> {
    List<Project> findByProjectNameContaining(String word);

    Page<Project> findAll(Pageable pageable);
}

