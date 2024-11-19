package com.example.proyectoServidor.model;

import java.sql.Date;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

@Entity
public class Project {
     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int projectId;

    private String projectName;
    private String description;
    private Date startDate;
    private Date endDate;
    private String repositoryUrl;
    private String demoUrl;
    private String picture;

    @ManyToOne
    @JoinColumn(name = "status_status_id")
    private Status status;

    
    /*Aqui va la relacion technologies_used_in_projects */

    @ManyToMany
    @JoinTable(
      name = "technologies_used_in_projects", 
      joinColumns = @JoinColumn(name = "projects_project_id"), 
      inverseJoinColumns = @JoinColumn(name = "technologies_tech_id"))
    private List<Technology> technologies;

    /*Aqui va la relacion developers_worked_on_projects */

    @ManyToMany
    @JoinTable(
      name = "developers_worked_on_projects", 
      joinColumns = @JoinColumn(name = "projects_project_id"), 
      inverseJoinColumns = @JoinColumn(name = "developers_dev_id"))
    private List<Developer> developers;

    // Getters y Setters
    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getRepositoryUrl() {
        return repositoryUrl;
    }

    public void setRepositoryUrl(String repositoryUrl) {
        this.repositoryUrl = repositoryUrl;
    }

    public String getDemoUrl() {
        return demoUrl;
    }

    public void setDemoUrl(String demoUrl) {
        this.demoUrl = demoUrl;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public List<Technology> getTechnologies() {
        return technologies;
    }

    public void setTechnologies(List<Technology> technologies) {
        this.technologies = technologies;
    }

    public List<Developer> getDevelopers() {
        return developers;
    }

    public void setDevelopers(List<Developer> developers) {
        this.developers = developers;
    }
    
}
