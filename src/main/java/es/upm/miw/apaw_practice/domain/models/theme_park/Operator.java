package es.upm.miw.apaw_practice.domain.models.theme_park;

import java.time.LocalDateTime;

public class Operator {
    private String name;
    private String address;
    private LocalDateTime registrationDate;
    private Ride ride;
    public Operator() {
        //empty from framework
    }

    public Operator(String name, String address, LocalDateTime registrationDate, Ride ride) {
        this.name = name;
        this.address = address;
        this.registrationDate = registrationDate;
        this.ride = ride;
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

    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDateTime registrationDate) { this.registrationDate = registrationDate; }

    public Ride getRide() {
        return ride;
    }

    public void setRide(Ride ride) {
        this.ride = ride;
    }

    @Override
    public String toString() {
        return "Operator{" +
                "name=" + name +
                ", address=" + address +
                ", registrationDate=" + registrationDate +
                ", Ride=" + ride +
                '}';
    }
}
