package bo.edu.ucbcba.hotel.controller;

import bo.edu.ucbcba.hotel.dao.usersEntityManager;
import bo.edu.ucbcba.hotel.exceptions.ValidationException;
import bo.edu.ucbcba.hotel.model.Employers;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * Created by Alejandra on 29/05/2016.
 */
public class EmployerController extends Component {

    public boolean exeptions(String firstName,String lastName, String ci, String phone) {
        int telf,id;
        boolean resp=true;
        if(ci.isEmpty()) {
            throw new ValidationException("Release ic cant be blank");
        }
        if(phone.isEmpty()) {
            throw new ValidationException("Release phone cant be blank");
        }
        if(lastName.isEmpty()) {
            throw new ValidationException("Release last name cant be blank");
        }
        if(firstName.isEmpty()) {
            throw new ValidationException("Release name cant be blank");
        }
        if (!ci.matches("[0-9]+")) {
            throw new ValidationException("Release ic is invalid");
        }
        if (!phone.matches("[0-9]+")) {
            throw new ValidationException("Release phone is invalid");
        }

        telf= Integer.parseInt(phone);
        id=Integer.parseInt(ci);

        if (id>999999999 && id<0) {
            throw new ValidationException("Release ic is invalid");
        }
        if (telf>99999999 && telf <0) {
            throw new ValidationException("Release phone is invalid");
        }
        if (firstName.length()>32) {
            throw new ValidationException("Release name is invalid");
        }
        if (lastName.length()>32) {
            throw new ValidationException("Release last name is invalid");
        }


        return resp;
    }
        public void create(String firstName,String lastName, int ci, int phone ){

        Employers e=new Employers();

        e.setCi(ci);
        e.setName(firstName);
        e.setLastName(lastName);
        e.setPhone(phone);

        EntityManager entityManager = usersEntityManager.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(e);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public static List<Employers> searchEmployers(String q) {
        int a;
        EntityManager entityManager = usersEntityManager.createEntityManager();

        if(q.matches("[0-9]+")){
            a=Integer.parseInt(q);
            TypedQuery<Employers> query = entityManager.createQuery("select s from Employers s WHERE s.Ci= :a", Employers.class);
            query.setParameter("a", a);
            List<Employers> response = query.getResultList();
            entityManager.close();
            return response;
        }
        TypedQuery<Employers> query = entityManager.createQuery("select s from Employers s WHERE lower(s.Name) like :q or lower(s.lastName) like :q", Employers.class);
        query.setParameter("q", "%" + q.toLowerCase() + "%");
        List<Employers> response = query.getResultList();
        entityManager.close();
        return response;
    }


    public void delete (String id) {

        EntityManager entityManager = usersEntityManager.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.remove(entityManager.find(Employers.class,(Integer.parseInt(id))));
        entityManager.getTransaction().commit();
        entityManager.close();

    }
}
