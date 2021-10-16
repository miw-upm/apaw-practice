package es.upm.miw.apaw_practice.domain.models.hotel;

import java.util.ArrayList;
import java.util.List;

public class Hotel {

    private String name;
    private String direction;
    private Integer numberStars;
    private List<Room> rooms;

    public Hotel() {
        //Empty because of framework
    }

    public Hotel(String name, String direction, Integer numberStars, List<Room> rooms) {
        this.name = name;
        this.direction = direction;
        this.numberStars = numberStars;
        this.rooms = rooms;
    }

    public Hotel(String name,String direction, Integer numStars) {
        this.name =  name;
        this.direction = direction;
        this.numberStars = numStars;
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

    public Integer getNumberStars() {
        return numberStars;
    }

    public void setNumberStars(Integer numberStars) {
        this.numberStars = numberStars;
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
                "name='" + name + '\'' +
                ", direction='" + direction + '\'' +
                ", numberStars=" + numberStars +
                ", rooms=" + rooms +
                '}';
    }
}
