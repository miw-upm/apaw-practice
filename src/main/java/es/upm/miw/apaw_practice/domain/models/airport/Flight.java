package es.upm.miw.apaw_practice.domain.models.airport;

import java.time.LocalDateTime;

public class Flight {
    private String number;
    private LocalDateTime date;
    private Boolean isCancelled;

    public Flight() {
        // empty for framework
    }

    public Flight(String number, LocalDateTime date, String surname, Boolean isCancelled) {
        this.number = number;
        this.date = date;
        this.isCancelled = isCancelled;
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
        return isCancelled;
    }

    public void setCancelled(Boolean cancelled) {
        isCancelled = cancelled;
    }

    @Override
    public String toString() {
        return "Flight{" +
                "number='" + this.number + '\'' +
                ", date='" + this.date + '\'' +
                ", isCancelled=" + this.isCancelled +
                '}';
    }
}
