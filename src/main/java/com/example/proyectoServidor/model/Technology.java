package com.example.proyectoServidor.model;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "technologies")
public class Technology implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tech_id")
    private Long techId;

    @Column(name = "tech_name")
    private String techName;

    @Column(name = "description")
    private String description;

    // Relación Many-to-Many con Project (una tecnología puede ser usada en varios proyectos)
    @ManyToMany(mappedBy = "technologies")
    private List<Project> projects;

    // Relación Many-to-Many con Developer (una tecnología puede ser utilizada por varios desarrolladores)
    @ManyToMany(mappedBy = "technologies")
    private List<Developer> developers;
}