package es.upm.miw.apaw_practice.domain.models.theme_park;

import java.time.LocalDateTime;

public class Operator {
    private String idEmployee;
    private String nick;
    private LocalDateTime registrationDate;
    private Ride ride;
    public Operator() {
        //empty from framework
    }

    public Operator(String idEmployee, String nick, LocalDateTime registrationDate, Ride ride) {
        this.idEmployee = idEmployee;
        this.nick = nick;
        this.registrationDate = registrationDate;
        this.ride = ride;
    }

    public String getIdEmployee() {
        return idEmployee;
    }

    public void setIdEmployee(String idEmployee) {
        this.idEmployee = idEmployee;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
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
                ", nick=" + nick +
                ", registrationDate=" + registrationDate +
                ", Ride=" + ride +
                '}';
    }
}
