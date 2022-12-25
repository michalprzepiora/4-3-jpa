package com.kodilla.jpa.domain;

import lombok.Getter;

import javax.persistence.*;
import java.util.List;

@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Status status;
    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name = "Task_responsiblePersons",
            joinColumns = @JoinColumn(name = "Task_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "responsiblePersons_id", referencedColumnName = "id"))
    private List<Person> responsiblePersons = new java.util.ArrayList<>();
    @OneToMany(cascade=CascadeType.ALL)
    private List<SubTask> subTasks;

    public Task() {
    }

    public Task(Long id, String name, Status status, List<Person> responsiblePersons, List<SubTask> subTasks) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.responsiblePersons = responsiblePersons;
        this.subTasks = subTasks;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public List<Person> getResponsiblePersons() {
        return responsiblePersons;
    }

    public void setResponsiblePersons(List<Person> responsiblePersons) {
        this.responsiblePersons = responsiblePersons;
    }

    public List<SubTask> getSubTasks() {
        return subTasks;
    }

    public void setSubTasks(List<SubTask> subTasks) {
        this.subTasks = subTasks;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", status=" + status +
                ", responsiblePersons=" + responsiblePersons +
                ", subTasks=" + subTasks +
                '}';
    }
}
