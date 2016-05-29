package bo.edu.ucbcba.hotel.controller;

import bo.edu.ucbcba.hotel.dao.usersEntityManager;
import bo.edu.ucbcba.hotel.model.Employers;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by Alejandra on 29/05/2016.
 */
public class EmployerController {
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

    public static List<Employers> searchEmployers(String CI) {
        int ci=0;
        if(CI.matches("[0-9]+")){
            if(CI.isEmpty()) {
                ci=0;
            } else {
                ci=Integer.parseInt(CI);
            }
        }
        EntityManager entityManager = usersEntityManager.createEntityManager();
        if(ci!=0)
        {
            TypedQuery<Employers> query = entityManager.createQuery("select s from Employers s where s.ClientCi= :a ", Employers.class);
            query.setParameter("a", ci);
            List<Employers> response = query.getResultList();
            entityManager.close();
            return response;
        }
        if (ci == 0) {
            TypedQuery<Employers> query = entityManager.createQuery("select s from Employers s ", Employers.class);
            //query.setParameter("a", a);
            List<Employers> response = query.getResultList();
            entityManager.close();
            return response;
        }
        return null;
    }
    public void delete (String id) {

        EntityManager entityManager = usersEntityManager.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.remove(entityManager.find(Employers.class,(Integer.parseInt(id))));
        entityManager.getTransaction().commit();
        entityManager.close();

    }
}
