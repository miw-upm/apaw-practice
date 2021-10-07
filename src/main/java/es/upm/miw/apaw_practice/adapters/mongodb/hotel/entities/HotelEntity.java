package es.upm.miw.apaw_practice.adapters.mongodb.hotel.entities;

import es.upm.miw.apaw_practice.domain.models.hotel.Director;
import es.upm.miw.apaw_practice.domain.models.hotel.Hotel;
import es.upm.miw.apaw_practice.domain.models.hotel.Room;
import nonapi.io.github.classgraph.json.Id;
import org.springframework.beans.BeanUtils;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Document
public class HotelEntity {
    @Id
    private String id;
    private String direction;
    private Integer numberStars;
    @DBRef
    private DirectorEntity director;
    private List<RoomEntity> roomEntities;

    public HotelEntity() {
        //empty for framework
    }

    public HotelEntity(String direction, Integer numberStars, DirectorEntity director, List<RoomEntity> roomEntities) {
        this.id = UUID.randomUUID().toString();
        this.direction = direction;
        this.numberStars = numberStars;
        this.director = director;
        this.roomEntities = roomEntities;
    }

    public Hotel toHotel() {
        Hotel hotel = new Hotel();
        BeanUtils.copyProperties(this, hotel, "rooms", "director");
        List<Room> rooms = this.roomEntities.stream()
                .map(RoomEntity::toRoom)
                .collect(Collectors.toList());
        Director director = this.director.toDirector();
        hotel.setRooms(rooms);
        hotel.setDirector(director);
        return hotel;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public DirectorEntity getDirector() {
        return director;
    }

    public void setDirector(DirectorEntity director) {
        this.director = director;
    }

    public List<RoomEntity> getRoomEntities() {
        return roomEntities;
    }

    public void setRooms(List<RoomEntity> roomEntities) {
        this.roomEntities = roomEntities;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, direction, numberStars, director, roomEntities);
    }

    @Override
    public String toString() {
        return "HotelEntity{" +
                "id='" + id + '\'' +
                ", direction='" + direction + '\'' +
                ", numberStars=" + numberStars +
                ", director=" + director +
                ", roomEntities=" + roomEntities +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HotelEntity that = (HotelEntity) o;
        return id.equals(that.id) && Objects.equals(direction, that.direction) && Objects.equals(numberStars, that.numberStars) && Objects.equals(director, that.director) && Objects.equals(roomEntities, that.roomEntities);
    }


}
