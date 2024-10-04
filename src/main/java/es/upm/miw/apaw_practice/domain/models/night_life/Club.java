package es.upm.miw.apaw_practice.domain.models.night_life;

import java.util.List;

public class Club {
    public String name;
    public int capacity;
    public boolean opened;
    private Owner owner;
    private List<Reservation> reservations;

    public Club() {
        //empty for framework
    }

    public Club(String name, int capacity, boolean opened, Owner owner, List<Reservation> reservations) {
        this.name = name;
        this.capacity = capacity;
        this.opened = opened;
        this.owner = owner;
        this.reservations = reservations;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public boolean isOpened() {
        return opened;
    }
    public void setOpened(boolean opened) {
        this.opened = opened;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }

    @Override
    public String toString() {
        return "Club{" +
                "name='" + name + '\'' +
                ", capacity=" + capacity +
                ", isOpen=" + opened +
                ", owner=" + (owner != null ? owner.getName() : "None") +
                ", reservations=" + reservations.size() +
                '}';
    }
}
