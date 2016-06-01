package bo.edu.ucbcba.hotel.model;


import javax.persistence.*;
@Entity
public class Salons {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id; // Primary Key, and Auto Generated

    @Column(length = 100)
    private String name;
    private boolean availability;
    private int capacity;

    public Salons(String name, boolean availability, int capacity) {
        this.name = name;
        this.availability = availability;
        this.capacity = capacity;
    }
    public Salons(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isAvailability() {
        return availability;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}

