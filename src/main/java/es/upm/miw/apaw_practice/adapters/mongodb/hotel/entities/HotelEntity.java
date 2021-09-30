package es.upm.miw.apaw_practice.adapters.mongodb.hotel.entities;

import nonapi.io.github.classgraph.json.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Objects;

@Document
public class HotelEntity {
    @Id
    private String id;
    private String direction;
    private Integer numberStars;
    @DBRef
    private DirectorEntity director;
    @DBRef
    private List<RoomEntity> rooms;

    public HotelEntity(){
        //empty for framework
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

    public List<RoomEntity> getRooms() {
        return rooms;
    }

    public void setRooms(List<RoomEntity> rooms) {
        this.rooms = rooms;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, direction, numberStars, director, rooms);
    }

    @Override
    public String toString() {
        return "HotelEntity{" +
                "id='" + id + '\'' +
                ", direction='" + direction + '\'' +
                ", numberStars=" + numberStars +
                ", director=" + director +
                ", rooms=" + rooms +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HotelEntity that = (HotelEntity) o;
        return id.equals(that.id) && Objects.equals(direction, that.direction) && Objects.equals(numberStars, that.numberStars) && Objects.equals(director, that.director) && Objects.equals(rooms, that.rooms);
    }

}
