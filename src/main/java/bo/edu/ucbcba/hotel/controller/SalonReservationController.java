package bo.edu.ucbcba.hotel.controller;
import bo.edu.ucbcba.hotel.dao.usersEntityManager;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import bo.edu.ucbcba.hotel.model.SalonReservation;

public class SalonReservationController {

    public void create(String clientName, int clientCi, int day, String month, int year, int cantPersonas, String salonName){
        SalonReservation salonReservation = new SalonReservation();
        salonReservation.setClientName(clientName);
        salonReservation.setClientCi(clientCi);
        salonReservation.setDay(day);
        salonReservation.setMonth(month);
        salonReservation.setAnio(year);
        salonReservation.setCantPersonas(cantPersonas);
        salonReservation.setSalonName(salonName);



        EntityManager entityManager = usersEntityManager.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(salonReservation);
        entityManager.getTransaction().commit();
        entityManager.close();
    }
    public void update(String clientName,int clientCi,int day,String month,int year,int cantPersonas,String salonName,int id){

        EntityManager entityManager = usersEntityManager.createEntityManager();
        entityManager.getTransaction().begin();
        SalonReservation salonReservation= (SalonReservation)entityManager.find(SalonReservation.class ,id);
        salonReservation.setClientName(clientName);
        salonReservation.setClientCi(clientCi);
        salonReservation.setDay(day);
        salonReservation.setMonth(month);
        salonReservation.setAnio(year);
        salonReservation.setCantPersonas(cantPersonas);
        salonReservation.setSalonName(salonName);
        entityManager.getTransaction().commit();

        entityManager.close();

    }
    public static List<SalonReservation> searchSalon(String q) {
        int a=0;

        EntityManager entityManager = usersEntityManager.createEntityManager();
        if (q.isEmpty()) {
            a = 0;
            TypedQuery<SalonReservation> query = entityManager.createQuery("select s from SalonReservation s ", SalonReservation.class);
            //query.setParameter("a", a);
            List<SalonReservation> response = query.getResultList();
            entityManager.close();
            return response;
        }
        if(q.matches("[0-9]+"))
        {
            a=Integer.parseInt(q);
            TypedQuery<SalonReservation> query = entityManager.createQuery("select s from SalonReservation s where s.id= :a or s.clientCi= :a or s.cantPersonas= :a", SalonReservation.class);
            query.setParameter("a", a);
            List<SalonReservation> response = query.getResultList();
            entityManager.close();
            return response;
        }
        if(q.matches("[a-zA-Z]+"))
        {
            TypedQuery<SalonReservation> query = entityManager.createQuery("select s from SalonReservation s where lower(s.clientName) like :typo or lower(s.salonName) like :typo ", SalonReservation.class);
            query.setParameter("typo", "%" + q.toLowerCase() + "%");
            List<SalonReservation> response = query.getResultList();
            entityManager.close();
            return response;
        }

        return null;
    }
    public static List<SalonReservation> getRoom(int id){
        EntityManager entityManager = usersEntityManager.createEntityManager();
        TypedQuery<SalonReservation> query = entityManager.createQuery("select s from SalonReservation s where s.id= :a ", SalonReservation.class);
        query.setParameter("a", id);
        List<SalonReservation> response = query.getResultList();
        entityManager.close();
        return response;
    }

    public void DeleteRoom(String q)
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
            entityManager.remove(entityManager.find(SalonReservation.class,a));
            entityManager.getTransaction().commit();
            entityManager.close();
        }
    }


}
