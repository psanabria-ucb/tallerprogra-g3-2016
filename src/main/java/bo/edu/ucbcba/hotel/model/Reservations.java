package bo.edu.ucbcba.hotel.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * Created by CésarIvan on 02/06/2016.
 */
public class Reservations {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int rerserveNumber; // Primary Key, and Auto Generated

    private int cantDays;

    private Fecha fechaRes;

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

    public int getRerserveNumber() {
        return rerserveNumber;
    }

    public void setRerserveNumber(int rerserveNumber) {
        this.rerserveNumber = rerserveNumber;
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

    public Fecha getFechaRes() {
        return fechaRes;
    }

    public void setFechaRes(Fecha fechaRes) {
        this.fechaRes = fechaRes;
    }
}
