package bo.edu.ucbcba.hotel.controller;

import bo.edu.ucbcba.hotel.dao.usersEntityManager;
import bo.edu.ucbcba.hotel.exceptions.ValidationException;
import bo.edu.ucbcba.hotel.model.Services;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;


/**
 * Created by Alejandra on 17/05/2016.
 */
public class ServiceController {

    public boolean exemptions(String name, String description, String cost){

        if(cost.isEmpty())
            throw new ValidationException("Release cost cant be blank");
        if (!cost.matches("[0-9]+"))
            throw new ValidationException("Release cost is invalid");
        if (cost.length()>8)
            throw new ValidationException("Release cost is invalid");
        if (name.length()>32)
            throw new ValidationException("Release name is invalid");
        if (description.length()>32)
            throw new ValidationException("Release description is invalid");
        if (name.length()==0)
            throw new ValidationException("Release name cant be blank");
        if (description.length()==0)
            throw new ValidationException("Release description cant be blank");
        return true;
    }

    public void create(String name, String description, String cost){
        Services service = new Services();
        int costo;

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
        int a;
        List<Services> response = null;
        EntityManager entityManager = usersEntityManager.createEntityManager();
        if (q.isEmpty()) {
            TypedQuery<Services> query = entityManager.createQuery("select s from Services s ", Services.class);
            //query.setParameter("a", a);
            response = query.getResultList();
            entityManager.close();
        }
        if(q.matches("[0-9]+"))
        {
            a=Integer.parseInt(q);
            TypedQuery<Services> query = entityManager.createQuery("select s from Services s where s.roomNumber= :a or s.cost= :a", Services.class);
            query.setParameter("a", a);
            response = query.getResultList();
            entityManager.close();
        }
        if(q.matches("[a-zA-Z]+"))
        {
            TypedQuery<Services> query = entityManager.createQuery("select s from Services s where lower(s.name) like :typo or lower(s.description) like :typo ", Services.class);
            query.setParameter("typo", "%" + q.toLowerCase() + "%");
            response = query.getResultList();
            entityManager.close();
        }

		return response;
    }

    public void delete (String id) {

        EntityManager entityManager = usersEntityManager.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.remove(entityManager.find(Services.class,(Integer.parseInt(id))));
        entityManager.getTransaction().commit();
        entityManager.close();

    }

    public void update(String name, String description, String cost,int servicenum){



        EntityManager entityManager = usersEntityManager.createEntityManager();
        entityManager.getTransaction().begin();
        Services room= (Services)entityManager.find(Services.class ,servicenum);
        room.setCost(Integer.parseInt(cost));
        room.setDescription(description);
        room.setName(name);
        entityManager.getTransaction().commit();

        entityManager.close();

    }

    }

