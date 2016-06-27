package bo.edu.ucbcba.hotel.controller;

import bo.edu.ucbcba.hotel.dao.usersEntityManager;
import bo.edu.ucbcba.hotel.exceptions.ValidationException;
import bo.edu.ucbcba.hotel.model.Clients;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by CésarIvan on 19/05/2016.
 */
public class ClientController {

    public boolean exemptions(String firstName, String lastName, String ci, String phone){

        if (!ci.matches("[0-9]+"))
            throw new ValidationException("Release CI is invalid, only numbers");
        if (ci.length()>8) {
            throw new ValidationException("Release CI is so long");
        }
        if (!phone.matches("[0-9]+"))
            throw new ValidationException("Release phone is invalid, only numbers");
        if (phone.length()>8) {
            throw new ValidationException("Release phone is so long");
        }
        if (firstName.length()>15) {
            throw new ValidationException("Release first name is invalid, so long");
        }
        if (lastName.length()>15) {
            throw new ValidationException("Release last name is invalid, so long");
        }
        if (ci.length()==0) {
            throw new ValidationException("Release CI cant be blank");
        }
        if (phone.length()==0) {
            throw new ValidationException("Release phone cant be blank");
        }
        if (firstName.length()==0) {
            throw new ValidationException("Release first name cant be blank");
        }
        if (lastName.length()==0) {
            throw new ValidationException("Release last name cant be blank");
        }
        if(validarCliente(ci)){
            throw new ValidationException("Client exists");
        }
        return true;
    }

    public boolean validarCliente(String CI){
        int ci;
        ci=Integer.parseInt(CI);
        EntityManager entityManager = usersEntityManager.createEntityManager();
        TypedQuery<Clients> query = entityManager.createQuery("select s from Clients s where s.clientCi= :ci ", Clients.class);
        query.setParameter("ci", ci);
        List<Clients> response = query.getResultList();
        int a =response.size();
        entityManager.close();
        if(a!=0)
            return true;
        else
            return false;


    }

    public void create(String firstName,String lastName, int ci, int phone ){

        Clients client=new Clients();
        client.setClientCi(ci);
        client.setFirstName(firstName);
        client.setLastName(lastName);
        client.setPhone(phone);


        EntityManager entityManager = usersEntityManager.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(client);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public void update(String firstName,String lastName, int ci, int phone){

        EntityManager entityManager = usersEntityManager.createEntityManager();
        entityManager.getTransaction().begin();
        Clients client= (Clients) entityManager.find(Clients.class ,ci);
        client.setFirstName(firstName);
        client.setLastName(lastName);
        client.setPhone(phone);
        //client.setClientCi(ci);
        entityManager.getTransaction().commit();

        entityManager.close();

    }

    public List<Clients> searchClients(String CI) {
        int ci=0;
        if(CI.matches("[0-9]+")){
            if(CI.isEmpty()) {
                ci=0;
            } else {
                ci=Integer.parseInt(CI);
            }
            EntityManager entityManager = usersEntityManager.createEntityManager();
            if(ci!=0)
            {
                TypedQuery<Clients> query = entityManager.createQuery("select s from Clients s where s.clientCi= :ci ", Clients.class);
                query.setParameter("ci", ci);
                List<Clients> response = query.getResultList();
                entityManager.close();
                return response;
            }
            if (ci == 0) {
                TypedQuery<Clients> query = entityManager.createQuery("select s from Clients s ", Clients.class);
                //query.setParameter("a", a);
                List<Clients> response = query.getResultList();
                entityManager.close();
                return response;
            }
        }
        else{
            EntityManager entityManager = usersEntityManager.createEntityManager();
            TypedQuery<Clients> query = entityManager.createQuery("select s from Clients s WHERE lower(s.firstName) like :Name OR lower(s.lastName) like :Name", Clients.class);
            query.setParameter("Name", "%" + CI.toLowerCase() + "%");
            List<Clients> response = query.getResultList();
            entityManager.close();
            return response;
        }

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
            entityManager.remove(entityManager.find(Clients.class,a));
            entityManager.getTransaction().commit();
            entityManager.close();
        }

    }

    public List<Clients> getAllClients() {
        EntityManager em = usersEntityManager.createEntityManager();
        TypedQuery<Clients> query = em.createQuery("select d from Clients d order by d.firstName", Clients.class);
        List<Clients> list = query.getResultList();
        em.close();
        return list;
    }

}

