package com.kodilla.jpa.domain;

import javax.persistence.*;
import java.util.List;

@Entity
public class SubTask {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Status status;
    @ManyToMany(cascade = CascadeType.ALL)
    private List<Person> responsiblePersons;

    public SubTask() {
    }

    public SubTask(Long id, String name, Status status, List<Person> responsiblePersons) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.responsiblePersons = responsiblePersons;
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

    @Override
    public String toString() {
        return "SubTask{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", status=" + status +
                ", responsiblePersons=" + responsiblePersons +
                '}';
    }
}
