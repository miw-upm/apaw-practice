package es.upm.miw.apaw_practice.domain.models.hotel;

import es.upm.miw.apaw_practice.adapters.mongodb.hotel.entities.HotelClientEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.hotel.entities.HotelMainEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.hotel.entities.HotelRoomEntity;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

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

    public void setRooms(List<HotelRoom> hotelRooms) { this.rooms = rooms; }

    public List<HotelClient> getClients() { return this.clients; }

    public void setClients(List<HotelClient> HotelClients) { this.clients = clients; }

    public HotelMainEntity toHotelEntity() {
        HotelMainEntity hotelEntity = new HotelMainEntity();
        List<HotelClientEntity> clients = this.clients.stream()
                .map(HotelClient::toClientEntity)
                .collect(Collectors.toList());
        hotelEntity.setClients(clients);
        List<HotelRoomEntity> rooms = this.rooms.stream()
                .map(HotelRoom::toRoomEntity)
                .collect(Collectors.toList());
        hotelEntity.setRooms(rooms);
        BeanUtils.copyProperties(this, hotelEntity, "rooms", "clients");
        return hotelEntity;
    }
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