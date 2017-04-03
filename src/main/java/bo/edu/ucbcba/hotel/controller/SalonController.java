package bo.edu.ucbcba.hotel.controller;

import bo.edu.ucbcba.hotel.dao.usersEntityManager;
import bo.edu.ucbcba.hotel.model.Salons;
import bo.edu.ucbcba.hotel.exceptions.ValidationException;
import bo.edu.ucbcba.hotel.model.Services;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;
public class SalonController {
    public void create(String name, boolean availability, int capacity){
        Salons salons= new Salons(name,availability,capacity);
        EntityManager entityManager = usersEntityManager.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(salons);
        entityManager.getTransaction().commit();
        entityManager.close();
    }
    public static List<Salons> searchSalon(String q) {
        int a;
        List<Salons> response = null;
        EntityManager entityManager = usersEntityManager.createEntityManager();
        if (q.isEmpty()) {

            TypedQuery<Salons> query = entityManager.createQuery("select s from Salons s ", Salons.class);
            //query.setParameter("a", a);
            response = query.getResultList();
            entityManager.close();
        }
        if(q.matches("[0-9]+"))
        {
            a=Integer.parseInt(q);
            TypedQuery<Salons> query = entityManager.createQuery("select s from Salons s where s.capacity= :a or s.id= :a", Salons.class);
            query.setParameter("a", a);
            response = query.getResultList();
            entityManager.close();
            
        }
        if(q.matches("[a-zA-Z]+"))
        {
            TypedQuery<Salons> query = entityManager.createQuery("select s from Salons s where lower(s.name) like :typo ", Salons.class);
            query.setParameter("typo", "%" + q.toLowerCase() + "%");
            response = query.getResultList();
            entityManager.close();
           
        }

        return response;
    }

    public void DeleteSalon(String q)
    {
        int a;
        if(q.matches("[0-9]+")){
            if(q.isEmpty()){
                a=0;
            }else{
                a=Integer.parseInt(q);
            }
            EntityManager entityManager = usersEntityManager.createEntityManager();
            entityManager.getTransaction().begin();
            entityManager.remove(entityManager.find(Salons.class,a));
            entityManager.getTransaction().commit();
            entityManager.close();
        }
    }
    public static List<Salons> getSalon(int num){
        EntityManager entityManager = usersEntityManager.createEntityManager();
        TypedQuery<Salons> query = entityManager.createQuery("select s from Salons s where s.id= :a ", Salons.class);
        query.setParameter("a", num);
        List<Salons> response = query.getResultList();
        entityManager.close();
        return response;
    }
    public void update(String name,int capacity,int roomNumber,boolean availability){
        EntityManager entityManager = usersEntityManager.createEntityManager();
        entityManager.getTransaction().begin();
        Salons salons= (Salons)entityManager.find(Salons.class ,roomNumber);
        salons.setName(name);
        salons.setCapacity(capacity);
        salons.setAvailability(availability);
        entityManager.getTransaction().commit();

        entityManager.close();

    }
    public static List<Salons> getAllRooms() {
        EntityManager em = usersEntityManager.createEntityManager();
        TypedQuery<Salons> query = em.createQuery("select s from Salons s", Salons.class);
        List<Salons> list = query.getResultList();
        em.close();
        return list;
    }
}
