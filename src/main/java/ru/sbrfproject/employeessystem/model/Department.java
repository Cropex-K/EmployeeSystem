package ru.sbrfproject.employeessystem.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Department implements Serializable{

    @Column(unique = true)
    private String name;
    private int hierarchy;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    public Department() {
    }

    public Department(String name, int hierarchy) {
        this.name = name;
        this.hierarchy = hierarchy;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHierarchy() {
        return hierarchy;
    }

    public void setHierarchy(int hierarchy) {
        this.hierarchy = hierarchy;
    }
}
