package bo.edu.ucbcba.hotel.model;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by CésarIvan on 26/06/2016.
 */
@Entity
public class Reservations {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int rerservationNumber; // Primary Key, and Auto Generated

    private int cantDays;
    private Dates dateRes;
    private boolean active;
    @ManyToOne
    private Clients client;
    @OneToMany
    private List<Rooms> rooms;

    public Reservations(){
        cantDays=0;
        active=false;
        client=null;
        rooms=new LinkedList();
    }


    public int getCantDays() {
        return cantDays;
    }

    public void setCantDays(int cantDays) {
        this.cantDays = cantDays;
    }

    public Dates getDateRes() {
        return dateRes;
    }

    public void setDateRes(Dates dateRes) {
        this.dateRes = dateRes;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Clients getClient() {
        return client;
    }

    public void setClient(Clients client) {
        this.client = client;
    }

    public List<Rooms> getRooms() {
        return rooms;
    }

    public void setRooms(List<Rooms> rooms) {
        this.rooms = rooms;
    }




}
