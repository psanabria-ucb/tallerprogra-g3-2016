package bo.edu.ucbcba.hotel.controller;

import bo.edu.ucbcba.hotel.dao.usersEntityManager;
import bo.edu.ucbcba.hotel.exceptions.ValidationException;
import bo.edu.ucbcba.hotel.model.Clients;
import bo.edu.ucbcba.hotel.model.Fecha;
import bo.edu.ucbcba.hotel.model.Reservas;
import bo.edu.ucbcba.hotel.model.Rooms;

import javax.persistence.EntityManager;

/**
 * Created by CésarIvan on 02/06/2016.
 */
public class ReserveController {

    public void create(String cantDays, Fecha fecha, Rooms room, Clients client){
        Reservas reserva = new Reservas();

        if (cantDays.matches("[0-9]+"))
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
            entityManager.remove(entityManager.find(Reservas.class,a));
            entityManager.getTransaction().commit();
            entityManager.close();
        }

    }



}
