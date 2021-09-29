package es.upm.miw.apaw_practice.domain.models.hotel;

import java.util.ArrayList;
import java.util.List;

public class Hotel {

    private String id;
    private String name;
    private String direction;
    private Integer numStars;
    private Director director;
    private List<Room> rooms;

    public Hotel() {
        //Empty because of framework
    }

    public Hotel(String id, String name, String direction, Integer numStars, Director director, List<Room> rooms) {
        this.id = id;
        this.name = name;
        this.direction = direction;
        this.numStars = numStars;
        this.director = director;
        this.rooms = rooms;
    }

    public Hotel(String id, String name, String direction, Integer numStars) {
        this.id = id;
        this.name = name;
        this.direction = direction;
        this.numStars = numStars;
        this.director = null;
        this.rooms = new ArrayList<>();
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

    public Integer getNumStars() {
        return numStars;
    }

    public void setNumStars(Integer numStars) {
        this.numStars = numStars;
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
                ", name='" + name + '\'' +
                ", direction='" + direction + '\'' +
                ", numStars=" + numStars +
                ", director=" + director +
                ", rooms=" + rooms +
                '}';
    }
}
