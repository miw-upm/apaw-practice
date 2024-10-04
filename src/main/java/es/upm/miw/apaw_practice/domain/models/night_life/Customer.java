package es.upm.miw.apaw_practice.domain.models.night_life;

import java.util.List;

public class Customer {
    private String name;
    private String phone;
    private String email;
    private List<Reservation> reservations;

    public Customer(){
        //empty for framework
    }

    public Customer(String name, String phone, String email, List<Reservation> reservations) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.reservations = reservations;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public List<Reservation> getReservations() {
        return reservations;
    }
    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }
    @Override
    public String toString() {
        return "Customer{" +
                "name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", reservations=" + reservations.size() +
                '}';
    }

}
