package com.example.proyectoServidor.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonManagedReference;  // Asegúrate de importar esta clase

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "projects")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "project_id")
    private Integer projectId;

    @Column(name = "project_name", nullable = false, unique = true)
    private String projectName;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name = "repository_url")
    private String repositoryUrl;

    @Column(name = "demo_url")
    private String demoUrl;

    @Column(name = "picture")
    private String picture;

    @ManyToOne(fetch = FetchType.EAGER)  // Asegúrate de tener el FetchType correcto
    @JoinColumn(name = "status_status_id", nullable = false)
    private Status status;

    @ManyToMany
    @JoinTable(
        name = "technologies_used_in_projects",
        joinColumns = @JoinColumn(name = "projects_project_id"),
        inverseJoinColumns = @JoinColumn(name = "technologies_tech_id")
    )
    @JsonManagedReference  // Maneja la serialización de esta relación
    private List<Technology> technologies;

    @ManyToMany
    @JoinTable(
        name = "developers_worked_on_projects",
        joinColumns = @JoinColumn(name = "projects_project_id"),
        inverseJoinColumns = @JoinColumn(name = "developers_dev_id")
    )
    @JsonManagedReference  // Maneja la serialización de esta relación
    private List<Developer> developers;
}
