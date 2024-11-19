package com.example.proyectoServidor.model;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "projects")
public class Project implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "project_id")
    private Long projectId;

    @Column(name = "project_name")
    @NotNull
    @Size(min = 1, max = 100)
    private String projectName;

    @Column(name = "description")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "status_status_id")
    private Status status;

    // Relación Many-to-Many con tecnologías
    @ManyToMany
    @JoinTable(
      name = "technologies_used_in_projects",
      joinColumns = @JoinColumn(name = "projects_project_id"),
      inverseJoinColumns = @JoinColumn(name = "technologies_tech_id"))
    private List<Technology> technologies;

    // Relación Many-to-Many con desarrolladores
    @ManyToMany
    @JoinTable(
      name = "developers_worked_on_projects",
      joinColumns = @JoinColumn(name = "projects_project_id"),
      inverseJoinColumns = @JoinColumn(name = "developers_dev_id"))
    private List<Developer> developers;
}
