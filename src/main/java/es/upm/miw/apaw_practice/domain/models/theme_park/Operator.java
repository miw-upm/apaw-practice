package es.upm.miw.apaw_practice.domain.models.theme_park;

import java.time.LocalDateTime;

public class Operator {
    private String idEmployee;
    private String address;
    private LocalDateTime registrationDate;
    private Ride ride;
    public Operator() {
        //empty from framework
    }

    public Operator(String idEmployee, String address, LocalDateTime registrationDate, Ride ride) {
        this.idEmployee = idEmployee;
        this.address = address;
        this.registrationDate = registrationDate;
        this.ride = ride;
    }

    public String getIdEmployee() {
        return idEmployee;
    }

    public void setIdEmployee(String idEmployee) {
        this.idEmployee = this.idEmployee;
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
                "idEmployee=" + idEmployee +
                ", address=" + address +
                ", registrationDate=" + registrationDate +
                ", Ride=" + ride +
                '}';
    }
}
