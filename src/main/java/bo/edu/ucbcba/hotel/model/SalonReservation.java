package bo.edu.ucbcba.hotel.model;
import javax.persistence.*;
/**
 * Created by Gabo on 26-Jun-16.
 */
@Entity
public class SalonReservation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id; // Primary Key, and Auto Generated

    @Column(length = 100)
    private String clientName;
    private int clientCi;
    private int day;
    private String month;
    private int year;
    private int  cantPersonas;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private String salonName;

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public int getClientCi() {
        return clientCi;
    }

    public void setClientCi(int clientCi) {
        this.clientCi = clientCi;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getCantPersonas() {
        return cantPersonas;
    }

    public void setCantPersonas(int cantPersonas) {
        this.cantPersonas = cantPersonas;
    }

    public String getSalonName() {
        return salonName;
    }

    public void setSalonName(String salonName) {
        this.salonName = salonName;
    }
}
