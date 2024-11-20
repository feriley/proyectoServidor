package com.example.proyectoServidor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.proyectoServidor.model.Developer;

@Repository
public interface DeveloperRepository extends JpaRepository<Developer, Integer> {

    
}
// Accede y maneja la base de datos