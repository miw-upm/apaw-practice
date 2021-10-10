package es.upm.miw.apaw_practice.domain.models.hotel;

import java.util.ArrayList;
import java.util.List;

public class Hotel {

    private String direction;
    private Integer numberStars;
    private Director director;
    private List<Room> rooms;

    public Hotel() {
        //Empty because of framework
    }

    public Hotel(String direction, Integer numberStars, Director director, List<Room> rooms) {
        this.direction = direction;
        this.numberStars = numberStars;
        this.director = director;
        this.rooms = rooms;
    }

    public Hotel(String direction, Integer numStars) {
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


    @Override
    public String toString() {
        return "Hotel{" +
                ", direction='" + direction + '\'' +
                ", numberStars=" + numberStars +
                ", director=" + director +
                ", rooms=" + rooms +
                '}';
    }
}
