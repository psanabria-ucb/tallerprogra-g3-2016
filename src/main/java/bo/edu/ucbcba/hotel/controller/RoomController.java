package bo.edu.ucbcba.hotel.controller;

import bo.edu.ucbcba.hotel.model.Rooms;
import bo.edu.ucbcba.hotel.dao.usersEntityManager;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class RoomController {

    public void create(String type,String view, boolean availability, boolean phone,boolean living,
                       boolean kitchenAccesories,boolean minibar,boolean desk,boolean ornaments){
        Rooms room = new Rooms();


        room.setType(type);
        room.setRoomView(view);
        room.setPhone(phone);
        room.setLiving(living);
        room.setKitchenAccesories(kitchenAccesories);
        room.setMinibar(minibar);
        room.setDesk(desk);
        room.setOrnaments(ornaments);

        EntityManager entityManager = usersEntityManager.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(room);
        entityManager.getTransaction().commit();
        entityManager.close();
    }
    public void update(String type,String view, boolean availability, boolean phone,boolean living,
                       boolean kitchenAccesories,boolean minibar,boolean desk,boolean ornaments,int roomNumber){

        EntityManager entityManager = usersEntityManager.createEntityManager();
        entityManager.getTransaction().begin();
        Rooms room= (Rooms)entityManager.find(Rooms.class ,roomNumber);
        room.setType(type);
        room.setRoomView(view);
        room.setPhone(phone);
        room.setLiving(living);
        room.setKitchenAccesories(kitchenAccesories);
        room.setMinibar(minibar);
        room.setDesk(desk);
        room.setOrnaments(ornaments);
        entityManager.getTransaction().commit();

        entityManager.close();

    }
    public static List<Rooms> searchRoom(String q) {
        int a;

        EntityManager entityManager = usersEntityManager.createEntityManager();
        if (q.isEmpty()) {
            TypedQuery<Rooms> query = entityManager.createQuery("select s from Rooms s ", Rooms.class);
            //query.setParameter("a", a);
            List<Rooms> response = query.getResultList();
            entityManager.close();
            return response;
        }
        if(q.matches("[0-9]+"))
        {
            a=Integer.parseInt(q);
            TypedQuery<Rooms> query = entityManager.createQuery("select s from Rooms s where s.roomNumber= :a ", Rooms.class);
            query.setParameter("a", a);
            List<Rooms> response = query.getResultList();
            entityManager.close();
            return response;
        }
        if(q.matches("[a-zA-Z]+"))
        {
            TypedQuery<Rooms> query = entityManager.createQuery("select s from Rooms s where lower(s.type) like :typo or lower(s.roomView) like :typo ", Rooms.class);
            query.setParameter("typo", "%" + q.toLowerCase() + "%");
            List<Rooms> response = query.getResultList();
            entityManager.close();
            return response;
        }

        return null;
    }
    public static List<Rooms> getRoom(int num){
        EntityManager entityManager = usersEntityManager.createEntityManager();
        TypedQuery<Rooms> query = entityManager.createQuery("select s from Rooms s where s.roomNumber= :a ", Rooms.class);
        query.setParameter("a", num);
        List<Rooms> response = query.getResultList();
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
            entityManager.remove(entityManager.find(Rooms.class,a));
            entityManager.getTransaction().commit();
            entityManager.close();
        }
    }

    public List<Rooms> getAllRooms() {
        EntityManager em = usersEntityManager.createEntityManager();
        TypedQuery<Rooms> query = em.createQuery("select d from Rooms d order by d.roomNumber", Rooms.class);
        List<Rooms> list = query.getResultList();
        em.close();
        return list;
    }
}
