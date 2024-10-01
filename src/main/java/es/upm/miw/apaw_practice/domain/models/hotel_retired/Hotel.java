package es.upm.miw.apaw_practice.domain.models.hotel_retired;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class Hotel {

    private String cif;
    private String name;
    private String address;
    private LocalDateTime dateOfConstruction;
    private List<Room> rooms;

    public Hotel() {
        // empty for framework
    }

    public Hotel(String cif, String name, String address, LocalDateTime dateOfConstruction, List<Room> rooms) {
        this.cif = cif;
        this.name = name;
        this.address = address;
        this.dateOfConstruction = dateOfConstruction;
        this.rooms = rooms;
    }

    public String getCif() {
        return cif;
    }

    public void setCif(String cif) {
        this.cif = cif;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDateTime getDateOfConstruction() {
        return dateOfConstruction;
    }

    public void setDateOfConstruction(LocalDateTime dateOfConstruction) {
        this.dateOfConstruction = dateOfConstruction;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Hotel hotel = (Hotel) o;
        return Objects.equals(cif, hotel.cif) && Objects.equals(name, hotel.name) && Objects.equals(address, hotel.address) && Objects.equals(dateOfConstruction, hotel.dateOfConstruction) && Objects.equals(rooms, hotel.rooms);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cif, name, address, dateOfConstruction, rooms);
    }

    @Override
    public String toString() {
        return "Hotel{" +
                "cif='" + cif + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", dateOfConstruction=" + dateOfConstruction +
                ", rooms=" + rooms +
                '}';
    }
}
