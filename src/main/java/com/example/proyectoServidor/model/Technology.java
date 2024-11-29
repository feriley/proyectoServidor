package com.example.proyectoServidor.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.fasterxml.jackson.annotation.JsonBackReference;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "technologies")
public class Technology {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tech_id")
    private Integer techId;

    @Column(name = "tech_name", nullable = false, unique = true)
    private String techName;

    @ManyToMany(mappedBy = "technologies")
    @JsonBackReference  // Evita la recursión infinita
    private List<Project> projects;
}
