package bo.edu.ucbcba.hotel.controller;

import bo.edu.ucbcba.hotel.dao.usersEntityManager;
import bo.edu.ucbcba.hotel.exceptions.ValidationException;
import bo.edu.ucbcba.hotel.model.Clients;
import bo.edu.ucbcba.hotel.model.Fecha;
import bo.edu.ucbcba.hotel.model.Reservations;
import bo.edu.ucbcba.hotel.model.Rooms;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by CésarIvan on 02/06/2016.
 */
public class ReservationController {

    public void create(String cantDays, Fecha fecha, Rooms room, Clients client){
        Reservations reserva = new Reservations();

        if (cantDays.matches("[0-9]+"))
            if(Integer.parseInt(cantDays)>10 || Integer.parseInt(cantDays)<1)
                throw new ValidationException("You must choice 1-10 days");
            else
                reserva.setCantDays(Integer.parseInt(cantDays));
        else
            throw new ValidationException("Release year isn't a number");

        reserva.setClient(client);
        reserva.setFechaRes(fecha);
        reserva.setRoom(room);


        EntityManager entityManager = usersEntityManager.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(reserva);
        entityManager.getTransaction().commit();
        entityManager.close();

    }
    public void update(int cantDays, Fecha fecha, Rooms room, Clients client,int reserveNumber){

        EntityManager entityManager = usersEntityManager.createEntityManager();
        entityManager.getTransaction().begin();
        Reservations reserva= (Reservations) entityManager.find(Reservations.class ,reserveNumber);
        reserva.setCantDays(cantDays);
        reserva.setRoom(room);
        reserva.setClient(client);
        entityManager.getTransaction().commit();

        entityManager.close();

    }

    public static List<Reservations> searchReservation(String q) {
        int a=0;

        EntityManager entityManager = usersEntityManager.createEntityManager();
        if (q.isEmpty()) {
            a = 0;
            TypedQuery<Reservations> query = entityManager.createQuery("select s from Reservations s ", Reservations.class);
            //query.setParameter("a", a);
            List<Reservations> response = query.getResultList();
            entityManager.close();
            return response;
        }
        if(q.matches("[0-9]+"))
        {
            a=Integer.parseInt(q);
            TypedQuery<Reservations> query = entityManager.createQuery("select s from Reservations s where s.reserveNumber= :a ", Reservations.class);
            query.setParameter("a", a);
            List<Reservations> response = query.getResultList();
            entityManager.close();
            return response;
        }/*
        if(q.matches("[a-zA-Z]+"))
        {
            TypedQuery<Rooms> query = entityManager.createQuery("select s from Rooms s where lower(s.type) like :typo or lower(s.roomView) like :typo ", Rooms.class);
            query.setParameter("typo", "%" + q.toLowerCase() + "%");
            List<Rooms> response = query.getResultList();
            entityManager.close();
            return response;
        }*/

        return null;
       /* else{
            EntityManager entityManager = usersEntityManager.createEntityManager();
            TypedQuery<Reservations> query = entityManager.createQuery("select s from Reservations s WHERE lower(s.Clients.firstName) like :Name OR lower(s.Clients.lastName) like :Name", Reservations.class);
            query.setParameter("Name", "%" + number.toLowerCase() + "%");
            List<Reservations> response = query.getResultList();
            entityManager.close();
            return response;
        }*/

    }

    public void delete (String q) {

        int a;
        if(q.matches("[0-9]+")){
            if(q.isEmpty()){
                a=0;
            }else{
                a=Integer.parseInt(q);
            }
            EntityManager entityManager = usersEntityManager.createEntityManager();
            entityManager.getTransaction().begin();
            entityManager.remove(entityManager.find(Reservations.class,a));
            entityManager.getTransaction().commit();
            entityManager.close();
        }

    }



}
