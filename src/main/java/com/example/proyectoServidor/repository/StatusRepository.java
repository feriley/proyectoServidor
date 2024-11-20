package com.example.proyectoServidor.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.proyectoServidor.model.Status;



@Repository
public interface StatusRepository extends JpaRepository<Status, Integer> {
    
}
// Accede y maneja la base de datos