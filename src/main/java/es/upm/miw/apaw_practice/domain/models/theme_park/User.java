package es.upm.miw.apaw_practice.domain.models.theme_park;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class User {
    private String name;
    private String address;
    private List<Ride> rides;
    private LocalDateTime entranceDate;

    public User() {
        //empty for framework
    }

    public User(String name, String address, List<Ride> rides) {
        this.name = name;
        this.address = address;
        this.rides = rides;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDateTime getEntranceDate() {
        return entranceDate;
    }

    public void setEntranceDate(LocalDateTime entranceDate) {
        this.entranceDate = entranceDate;
    }

    public List<Ride> getRides() {
        return this.rides;
    }

    public void setRides(List<Ride> rides) {
        this.rides = rides;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", entranceDate='" + entranceDate + '\'' +
                ", ridesName=" + rides.stream().map(Ride::getName).collect(Collectors.toList()) +
                '}';
    }
}
