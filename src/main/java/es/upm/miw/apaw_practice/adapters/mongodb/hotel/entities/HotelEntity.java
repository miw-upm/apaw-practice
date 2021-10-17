package es.upm.miw.apaw_practice.adapters.mongodb.hotel.entities;

import es.upm.miw.apaw_practice.domain.models.hotel.Hotel;
import es.upm.miw.apaw_practice.domain.models.hotel.Room;
import nonapi.io.github.classgraph.json.Id;
import org.springframework.beans.BeanUtils;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Document
public class HotelEntity {

    @Id
    private String id;
    private String name;
    private String direction;
    private Integer numberStars;
    private List<RoomEntity> roomEntities;

    public HotelEntity() {
        //empty for framework
    }


    public HotelEntity(String name, String direction, Integer numberStars, List<RoomEntity> roomEntities) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.direction = direction;
        this.numberStars = numberStars;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public Integer getNumberStars() {
        return numberStars;
    }

    public void setNumberStars(Integer numberStars) {
        this.numberStars = numberStars;
    }

    public List<RoomEntity> getRoomEntities() {
        return roomEntities;
    }

    public void setRoomsEntities(List<RoomEntity> roomEntities) {
        this.roomEntities = roomEntities;
    }

    @Override
    public String toString() {
        return "HotelEntity{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", direction='" + direction + '\'' +
                ", numberStars=" + numberStars +
                ", roomEntities=" + roomEntities +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HotelEntity that = (HotelEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(direction, that.direction) && Objects.equals(numberStars, that.numberStars) && Objects.equals(roomEntities, that.roomEntities);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, direction, numberStars, roomEntities);
    }



}
