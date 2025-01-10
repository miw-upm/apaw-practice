package es.upm.miw.apaw_practice.domain.models.hotel;

import java.util.List;

public class HotelMain {
    private String name;
    private String address;
    private String phone;
    private List<HotelRoom> rooms;
    private List<HotelClient> clients;

    public HotelMain() {

    }

    public HotelMain(String name, String address, String phone, List<HotelRoom> rooms, List<HotelClient> clients) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.rooms = rooms;
        this.clients = clients;
    }

    public String getAddress() {
        return address;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<HotelRoom> getRooms() {
        return rooms;
    }

    public void setRooms(List<HotelRoom> rooms) { this.rooms = rooms; }

    public List<HotelClient> getClients() { return this.clients; }

    public void setClients(List<HotelClient> clients) { this.clients = clients; }

    @Override
    public String toString() {
        return "HotelMain{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", hotelRooms=" + rooms +
                ", HotelClients=" + clients +
                '}';
    }

}