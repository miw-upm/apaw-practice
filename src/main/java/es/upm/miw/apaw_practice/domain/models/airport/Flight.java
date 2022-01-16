package es.upm.miw.apaw_practice.domain.models.airport;

import java.time.LocalDateTime;
import java.util.List;

public class Flight {
    private String number;
    private LocalDateTime date;
    private Boolean cancelled;
    private Airplane airplane;
    private List<Pilot> pilots;
    private List<Passenger> passengers;

    public Flight() {
        // empty for framework
    }

    public Flight(String number, LocalDateTime date, Boolean cancelled, Airplane airplane, List<Pilot> pilots, List<Passenger> passengers) {
        this.number = number;
        this.date = date;
        this.cancelled = cancelled;
        this.airplane = airplane;
        this.pilots = pilots;
        this.passengers = passengers;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Boolean getCancelled() {
        return cancelled;
    }

    public void setCancelled(Boolean cancelled) {
        this.cancelled = cancelled;
    }

    public Airplane getAirplane() {
        return airplane;
    }

    public void setAirplane(Airplane airplane) {
        this.airplane = airplane;
    }

    public List<Pilot> getPilots() {
        return pilots;
    }

    public void setPilots(List<Pilot> pilots) {
        this.pilots = pilots;
    }

    public List<Passenger> getPassengers() {
        return passengers;
    }

    public void setPassengers(List<Passenger> passengers) {
        this.passengers = passengers;
    }

    @Override
    public String toString() {
        return "Flight{" +
                "number='" + this.number + '\'' +
                ", date='" + this.date + '\'' +
                ", cancelled=" + this.cancelled +
                ", airplane=" + this.airplane.toString() +
                ", pilots=" + this.pilots.toString() +
                ", passengers=" + this.passengers.toString() +
                '}';
    }
}
