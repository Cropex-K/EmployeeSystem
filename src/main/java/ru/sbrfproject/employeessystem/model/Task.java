package ru.sbrfproject.employeessystem.model;

import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;

@Entity
public class Task {

    private String description;
    private String theme;
    private boolean completing;
    private String nameDepFrom;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "department_id", nullable = false)
    @JsonIgnore
    private Department  department;

    public Task() {
    }

    public Task(String description, String theme, String nameDepFrom) {
        this.description = description;
        this.theme = theme;
        this.completing = false;
        this.nameDepFrom = nameDepFrom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public boolean isCompleting() {
        return completing;
    }

    public void setCompleting(boolean completing) {
        this.completing = completing;
    }

    public String getNameDepFrom() {
        return nameDepFrom;
    }

    public void setNameDepFrom(String nameDepFrom) {
        this.nameDepFrom = nameDepFrom;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
