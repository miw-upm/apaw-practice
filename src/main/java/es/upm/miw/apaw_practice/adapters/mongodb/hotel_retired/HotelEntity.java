package es.upm.miw.apaw_practice.adapters.mongodb.hotel_retired;

import es.upm.miw.apaw_practice.domain.models.hotel_retired.Hotel;
import es.upm.miw.apaw_practice.domain.models.hotel_retired.Room;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

public class HotelEntity {
    @Id
    private String id;
    @Indexed(unique = true)
    private String cif;
    private String hotelName;
    private String address;
    private List<RoomEntity> roomEntities;

    public HotelEntity() {
        // empty for framework
    }

    public HotelEntity(String cif, String hotelName, String address, List<RoomEntity> roomEntities) {
        this.id = UUID.randomUUID().toString();
        this.cif = cif;
        this.hotelName = hotelName;
        this.address = address;
        this.roomEntities = roomEntities;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public List<RoomEntity> getRoomsEntities() {
        return roomEntities;
    }

    public void setRoomEntities(List<RoomEntity> roomEntities) {
        this.roomEntities = roomEntities;
    }

    public Hotel toHotel() {
        Hotel hotel = new Hotel();
        BeanUtils.copyProperties(this, hotel, "roomEntities");
        List<Room> rooms = this.roomEntities.stream()
                .map(RoomEntity::toRoom)
                .collect(Collectors.toList());
        hotel.setRooms(rooms);
        return hotel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HotelEntity that = (HotelEntity) o;
        return Objects.equals(cif, that.cif);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(cif);
    }

    @Override
    public String toString() {
        return "HotelEntity{" +
                "id='" + id + '\'' +
                ", cif='" + cif + '\'' +
                ", hotelName='" + hotelName + '\'' +
                ", address='" + address + '\'' +
                ", roomEntities=" + roomEntities +
                '}';
    }
}
