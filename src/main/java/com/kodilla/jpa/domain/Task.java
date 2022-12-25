package com.kodilla.jpa.domain;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Status status;
    @ManyToMany(cascade = {CascadeType.ALL})
    @LazyCollection(LazyCollectionOption.FALSE)
    private Set<Person> responsiblePersons;
    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(cascade = CascadeType.ALL)
    private Set<SubTask> subTasks;

    public Task() {
    }

    public Task(Long id, String name, Status status, Set<Person> responsiblePersons, Set<SubTask> subTasks) {
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

    public Set<Person> getResponsiblePersons() {
        return responsiblePersons;
    }

    public void setResponsiblePersons(Set<Person> responsiblePersons) {
        this.responsiblePersons = responsiblePersons;
    }

    public Set<SubTask> getSubTasks() {
        return subTasks;
    }

    public void setSubTasks(Set<SubTask> subTasks) {
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
