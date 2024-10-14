package es.upm.miw.apaw_practice.adapters.mongodb.hotel.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
public class HotelMainEntity {
    @Id
    private String id;
    private String name;
    private String address;
    private String phone;
    @DBRef
    private List<HotelRoomEntity> rooms;
    @DBRef
    private List<HotelClientEntity> clients;

    public HotelMainEntity() {

    }

    public HotelMainEntity(String name, String address, String phone, List<HotelRoomEntity> rooms, List<HotelClientEntity> clients) {
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

    public List<HotelRoomEntity> getRooms() {
        return rooms;
    }

    public void setRooms(List<HotelRoomEntity> rooms) {
        this.rooms = rooms;
    }

    public List<HotelClientEntity> getClients() { return this.clients; }

    public void setClients(List<HotelClientEntity> clients) { this.clients = clients; }

    @Override
    public String toString() {
        return "HotelMain{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", rooms=" + rooms +
                ", clients=" + clients +
                '}';
    }

}
