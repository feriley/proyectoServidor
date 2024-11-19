package com.example.proyectoServidor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.proyectoServidor.model.Technology;

@Repository
public interface TechnologyRepository extends JpaRepository<Technology, Long> {

    
} 
