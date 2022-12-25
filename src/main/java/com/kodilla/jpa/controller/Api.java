package com.kodilla.jpa.controller;

import com.kodilla.jpa.domain.Person;
import com.kodilla.jpa.domain.Status;
import com.kodilla.jpa.domain.SubTask;
import com.kodilla.jpa.domain.Task;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@RestController
public class Api {
    EntityManagerFactory entityManagerFactory;

    public Api(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    @GetMapping("add")
    public List<Task> addData() {
        Person ala = new Person(null, "Ala", "Kozlowska");
        Person karolina = new Person(null, "Karolina", "Musztarda");
        Person piotr = new Person(null, "Piotr", "Pioter");
        Person jarek = new Person(null, "Jaroslaw", "Donald");

        SubTask cook1 = new SubTask(null, "washing products", Status.DONE, Arrays.asList(ala, karolina));
        SubTask cook2 = new SubTask(null, "boiling water", Status.DONE, Arrays.asList(karolina, piotr));
        SubTask cook3 = new SubTask(null, "pasta preparation", Status.IN_WORK, Arrays.asList(jarek, piotr));
        SubTask cook4 = new SubTask(null, "serving on a plate", Status.TO_DO, Arrays.asList(karolina, piotr, ala, jarek));

        SubTask clean1 = new SubTask(null, "dust wiping", Status.DONE, List.of(ala));
        SubTask clean2 = new SubTask(null, "carpet cleaning", Status.IN_WORK, List.of(ala, piotr, jarek));
        SubTask clean3 = new SubTask(null, "washing windows", Status.IN_WORK, List.of(karolina));
        SubTask clean4 = new SubTask(null, "mopping the floor", Status.TO_DO, List.of(karolina, jarek));

        Task cooking = new Task(null, "Cooking", Status.IN_WORK, new HashSet<>(Arrays.asList(ala, karolina)), new HashSet<>(Arrays.asList(cook1, cook2, cook3, cook4)));
        Task cleaning = new Task(null, "Cleaning", Status.TO_DO, new HashSet<>(Arrays.asList(piotr, jarek)), new HashSet<>(Arrays.asList(clean1, clean2, clean3, clean4)));

        EntityManager manager = entityManagerFactory.createEntityManager();

        manager.getTransaction().begin();
        manager.persist(cooking);
        manager.persist(cleaning);
        manager.flush();
        manager.getTransaction().commit();

        return Arrays.asList(cooking, cleaning);
    }

    @GetMapping("get")
    public List<Task> getData() throws InterruptedException {
        EntityManager em = entityManagerFactory.createEntityManager();
        TypedQuery<Task> query = em.createQuery("from Task", Task.class);
        EntityGraph<Task> entityGraph = em.createEntityGraph(Task.class);
        entityGraph.addAttributeNodes("responsiblePersons");
        entityGraph.addSubgraph("subTasks").addAttributeNodes("responsiblePersons");

        query.setHint("javax.persistence.fetchgraph",entityGraph);

        List<Task> result = query.getResultList();
        Thread.sleep(5000);


        return result;
    }

}
