package com.example.proyectoServidor.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
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
@Table(name = "developers")
public class Developer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dev_id")
    private Integer devId;

    @NotBlank(message = "Developer name is mandatory")
    @Column(name = "dev_name", nullable = false)
    private String devName;

    @NotBlank(message = "Developer surname is mandatory")
    @Column(name = "dev_surname", nullable = false)
    private String devSurname;

    @Email(message = "Email should be valid")
    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Size(max = 200, message = "LinkedIn URL cannot exceed 200 characters")
    @Column(name = "linkedin_url", unique = true)
    private String linkedinUrl;

    @Size(max = 200, message = "GitHub URL cannot exceed 200 characters")
    @Column(name = "github_url", unique = true)
    private String githubUrl;

    @ManyToMany(mappedBy = "developers") // La relación es mapeada por el lado de Project
    @JsonBackReference // No se serializa esta relación desde Developer para evitar recursión
    private List<Project> projects;
}
