package bo.edu.ucbcba.hotel.model;

import javax.persistence.*;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by CésarIvan on 02/06/2016.
 */

public class Reservations {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int rerservationNumber; // Primary Key, and Auto Generated

    private int cantDays;

    private Fecha dateRes;

    @ManyToOne
    private Clients client;
    @ManyToOne
    private Rooms room;

    public Reservations(){
        cantDays=0;
    }

    public int getCantDays() {
        return cantDays;
    }

    public void setCantDays(int cantDays) {
        this.cantDays = cantDays;
    }

    public int getRerservationNumber() {
        return rerservationNumber;
    }

    public void setRerservationNumber(int rerservationNumber) {
        this.rerservationNumber = rerservationNumber;
    }

    public Clients getClient() {
        return client;
    }

    public void setClient(Clients client) {
        this.client = client;
    }

    public Rooms getRoom() {
        return room;
    }

    public void setRoom(Rooms room) {
        this.room = room;
    }

    public Fecha getDateRes() {
        return dateRes;
    }

    public void setDateRes(Fecha dateRes) {
        this.dateRes = dateRes;
    }
}
