package es.upm.miw.apaw_practice.domain.models.hotel;

import java.util.ArrayList;
import java.util.List;

public class Hotel {

    private String id;
    private String direction;
    private Integer numberStars;
    private Director director;
    private List<Room> rooms;

    public Hotel() {
        //Empty because of framework
    }

    public Hotel(String id, String direction, Integer numberStars, Director director, List<Room> rooms) {
        this.id = id;
        this.direction = direction;
        this.numberStars = numberStars;
        this.director = director;
        this.rooms = rooms;
    }

    public Hotel(String id, String direction, Integer numStars) {
        this.id = id;
        this.direction = direction;
        this.numberStars = numStars;
        this.director = null;
        this.rooms = new ArrayList<>();
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

    public Director getDirector() {
        return director;
    }

    public void setDirector(Director director) {
        this.director = director;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Hotel{" +
                "id='" + id + '\'' +
                ", direction='" + direction + '\'' +
                ", numberStars=" + numberStars +
                ", director=" + director +
                ", rooms=" + rooms +
                '}';
    }
}
