package com.example.proyectoServidor.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Technology {
    @Id
    private int techId;

    private String techName;

    // Getters y Setters
    public int getTechId() {
        return techId;
    }

    public void setTechId(int techId) {
        this.techId = techId;
    }

    public String getTechName() {
        return techName;
    }

    public void setTechName(String techName) {
        this.techName = techName;
    }
}
