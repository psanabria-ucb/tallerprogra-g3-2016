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

    public static List<Reservations> searchReservation(String number) {

        int num=0;
        if(number.matches("[0-9]+")){
            if(number.isEmpty()) {
                num=0;
            } else {
                num=Integer.parseInt(number);
            }
            EntityManager entityManager = usersEntityManager.createEntityManager();
            if(num!=0)
            {
                TypedQuery<Reservations> query = entityManager.createQuery("select s from Reservations s where s.rerserveNumber= :num ", Reservations.class);
                query.setParameter("num", num);
                List<Reservations> response = query.getResultList();
                entityManager.close();
                return response;
            }
            if (num == 0) {
                TypedQuery<Reservations> query = entityManager.createQuery("select s from Reservations s ", Reservations.class);
                //query.setParameter("a", a);
                List<Reservations> response = query.getResultList();
                entityManager.close();
                return response;
            }
        }
       /* else{
            EntityManager entityManager = usersEntityManager.createEntityManager();
            TypedQuery<Reservations> query = entityManager.createQuery("select s from Reservations s WHERE lower(s.Clients.firstName) like :Name OR lower(s.Clients.lastName) like :Name", Reservations.class);
            query.setParameter("Name", "%" + number.toLowerCase() + "%");
            List<Reservations> response = query.getResultList();
            entityManager.close();
            return response;
        }*/

        return null;
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
