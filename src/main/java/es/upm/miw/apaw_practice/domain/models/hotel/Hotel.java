package es.upm.miw.apaw_practice.domain.models.hotel;

import es.upm.miw.apaw_practice.domain.models.hotel.Room;

import java.util.List;

public class Hotel {
    private String name;
    private String address;
    private String phone;
    private List<Room> rooms;
    private List<Client> clients;

    public Hotel() {

    }

    public Hotel(String name, String address, String phone, List<Room> rooms, List<Client> clients) {
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

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

    public List<Client> getClients() { return this.clients; }

    public void setClients(final List<Client> clients) { this.clients = clients; }

    @Override
    public String toString() {
        return "Hotel{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", rooms=" + rooms +
                ", clients=" + clients +
                '}';
    }

}