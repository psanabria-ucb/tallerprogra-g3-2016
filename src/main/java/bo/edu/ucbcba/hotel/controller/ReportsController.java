package bo.edu.ucbcba.hotel.controller;

import bo.edu.ucbcba.hotel.dao.usersEntityManager;
import bo.edu.ucbcba.hotel.exceptions.ValidationException;
import bo.edu.ucbcba.hotel.model.Reports;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by CésarIvan on 27/06/2016.
 */
public class ReportsController {
    public boolean exemptions(String text, String type){


        if (text.length()==0) {
            throw new ValidationException("Release text cant be blank");
        }
        if (type.length()==0) {
            throw new ValidationException("Release type cant be blank");
        }
        return true;
    }
    public void create(String text,int day,int month,int year, String type){

        Reports report= new Reports();

        report.setText(text);
        report.setDate(day,month,year);
        report.setType(type);

        EntityManager entityManager = usersEntityManager.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(report);
        entityManager.getTransaction().commit();
        entityManager.close();

    }

    public void update(String text,int day,int month,int year, String type,int reportId){

        EntityManager entityManager = usersEntityManager.createEntityManager();
        entityManager.getTransaction().begin();
        Reports report= (Reports) entityManager.find(Reports.class ,reportId);
        report.setText(text);
        report.setDate(day,month,year);
        report.setType(type);
        entityManager.getTransaction().commit();

        entityManager.close();

    }

    public static List<Reports> searchReport(String q) {
        int a;
        EntityManager entityManager = usersEntityManager.createEntityManager();
        TypedQuery<Reports> query;
        List<Reports> response;
        if (q.isEmpty()) {
           query = entityManager.createQuery("select s from Reports s", Reports.class);
           response = query.getResultList();
            entityManager.close();
            return response;
        }
        if(q.matches("[0-9]+"))
        {
            a=Integer.parseInt(q);
             query = entityManager.createQuery("select s from Reports s where s.reportId= :a", Reports.class);
            query.setParameter("a", a);
            response = query.getResultList();
            entityManager.close();
            return response;
        }
        if(q.matches("[a-zA-Z]+"))
        {
            query = entityManager.createQuery("select s from Reports s where lower(s.type) like :type or lower(s.text) like :type", Reports.class);
            query.setParameter("type", "%" + q.toLowerCase() + "%");
             response = query.getResultList();
            entityManager.close();
            return response;
        }

        return null;
    }

    public static List<Reports> getReport(int num){
        EntityManager entityManager = usersEntityManager.createEntityManager();
        TypedQuery<Reports> query = entityManager.createQuery("select s from Reports s where s.reportId= :a ", Reports.class);
        query.setParameter("a", num);
        List<Reports> response = query.getResultList();
        entityManager.close();
        return response;
    }

    public void DeleteReport(String q)
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
            entityManager.remove(entityManager.find(Reports.class,a));
            entityManager.getTransaction().commit();
            entityManager.close();
        }
    }

    public List<Reports> getAllReports() {
        EntityManager em = usersEntityManager.createEntityManager();
        TypedQuery<Reports> query = em.createQuery("select d from Reports d order by d.reportId", Reports.class);
        List<Reports> list = query.getResultList();
        em.close();
        return list;
    }
}
