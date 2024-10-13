package es.upm.miw.apaw_practice.domain.models.hotel;

import java.util.List;

public class HotelMain {
    private String name;
    private String address;
    private String phone;
    private List<HotelRoom> hotelRooms;
    private List<HotelClient> HotelClients;

    public HotelMain() {

    }

    public HotelMain(String name, String address, String phone, List<HotelRoom> hotelRooms, List<HotelClient> HotelClients) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.hotelRooms = hotelRooms;
        this.HotelClients = HotelClients;
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
        return hotelRooms;
    }

    public void setRooms(List<HotelRoom> hotelRooms) {
        this.hotelRooms = hotelRooms;
    }

    public List<HotelClient> getClients() { return this.HotelClients; }

    public void setClients(final List<HotelClient> HotelClients) { this.HotelClients = HotelClients; }

    @Override
    public String toString() {
        return "HotelMain{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", hotelRooms=" + hotelRooms +
                ", HotelClients=" + HotelClients +
                '}';
    }

}