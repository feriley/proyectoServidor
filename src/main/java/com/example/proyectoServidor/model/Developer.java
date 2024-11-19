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
@Table(name = "developers")
public class Developer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "developer_id")
    private Long developerId;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    // Relación Many-to-Many con Project (un desarrollador puede trabajar en varios proyectos)
    @ManyToMany(mappedBy = "developers")
    private List<Project> projects;

    // Relación Many-to-Many con Technology (un desarrollador puede usar varias tecnologías)
    @ManyToMany(mappedBy = "developers")
    private List<Technology> technologies;
}