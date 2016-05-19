package bo.edu.ucbcba.hotel.controller;

import bo.edu.ucbcba.hotel.dao.usersEntityManager;
import bo.edu.ucbcba.hotel.exceptions.ValidationException;
import bo.edu.ucbcba.hotel.model.Services;


import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.swing.*;
import java.util.List;


/**
 * Created by Alejandra on 17/05/2016.
 */
public class ServiceController {
    public void create(String name, String description, String cost){
        Services service = new Services();
        int costo;
        if (!cost.matches("[0-9]+"))
            throw new ValidationException("Release cost isn't a number");
        costo=(Integer.parseInt(cost));

        service.setCost(costo);
        service.setDescription(description);
        service.setName(name);

        EntityManager entityManager = usersEntityManager.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(service);
        entityManager.getTransaction().commit();
        entityManager.close();
    }
    public List<Services> searchService(String q) {
        EntityManager entityManager = usersEntityManager.createEntityManager();
        TypedQuery<Services> query = entityManager.createQuery("select s from Services s WHERE lower(s.name) like :name", Services.class);
        query.setParameter("name", "%" + q.toLowerCase() + "%");
        List<Services> response = query.getResultList();
        entityManager.close();
        return response;
    }

    public void delete (String id) {

        EntityManager entityManager = usersEntityManager.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.remove(entityManager.find(Services.class,(Integer.parseInt(id))));
        entityManager.getTransaction().commit();
        entityManager.close();

    }



    }

