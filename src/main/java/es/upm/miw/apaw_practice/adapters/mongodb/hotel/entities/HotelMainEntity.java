package es.upm.miw.apaw_practice.adapters.mongodb.hotel.entities;

import es.upm.miw.apaw_practice.domain.models.hotel.HotelClient;
import es.upm.miw.apaw_practice.domain.models.hotel.HotelMain;
import es.upm.miw.apaw_practice.domain.models.hotel.HotelRoom;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;
import java.util.UUID;


@Document
public class HotelMainEntity {
    @Id
    private String id;
    @Indexed(unique = true)
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
        this.id = UUID.randomUUID().toString();
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

    public String getId() {
        return this.id;
    }

    public void setId(final String id) {
        this.id = id;
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

    public List<HotelClientEntity> getClients() {
        return this.clients;
    }

    public void setClients(List<HotelClientEntity> clients) {
        this.clients = clients;
    }


    public HotelMain toHotel() {
        HotelMain hotel = new HotelMain();
        BeanUtils.copyProperties(this, hotel, "rooms", "clients", "id");
        hotel.setId(this.id);
        List<HotelRoom> rooms = this.rooms.stream()
                .map(HotelRoomEntity::toRoom)
                .toList();
        hotel.setRooms(rooms);
        List<HotelClient> clients = this.clients.stream()
                .map(HotelClientEntity::toClient)
                .toList();
        hotel.setClients(clients);
        return hotel;
    }

    @Override
    public int hashCode() {
        return this.id.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return this == obj || obj != null && getClass() == obj.getClass() && (id.equals(((HotelMainEntity) obj).id));
    }

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
