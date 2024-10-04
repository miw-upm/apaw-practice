package es.upm.miw.apaw_practice.domain.models.hotel_retired;

import java.util.List;
import java.util.Objects;

public class Hotel {

    private String cif;
    private String hotelName;
    private String address;
    private List<Room> rooms;

    public Hotel() {
        // empty for framework
    }

    public Hotel(String cif, String hotelName, String address, List<Room> rooms) {
        this.cif = cif;
        this.hotelName = hotelName;
        this.address = address;
        this.rooms = rooms;
    }

    public String getCif() {
        return cif;
    }

    public void setCif(String cif) {
        this.cif = cif;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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
        return Objects.equals(cif, hotel.cif) && Objects.equals(hotelName, hotel.hotelName) && Objects.equals(address, hotel.address) && Objects.equals(rooms, hotel.rooms);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cif, hotelName, address, rooms);
    }

    @Override
    public String toString() {
        return "Hotel{" +
                "cif='" + cif + '\'' +
                ", hotelName='" + hotelName + '\'' +
                ", address='" + address + '\'' +
                ", rooms=" + rooms +
                '}';
    }
}
