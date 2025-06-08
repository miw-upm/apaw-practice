package es.upm.miw.apaw_practice.domain.models.cinema;

import java.time.LocalDateTime;
import java.util.List;

public class Screening {
    private LocalDateTime screeningTime;
    private Boolean threeDFormat;
    private Integer availableSeats;
    private List<Ticket> tickets;

    public Screening() {
        // Default constructor
    }

    public Screening(LocalDateTime screeningTime, Boolean threeDFormat, Integer availableSeats, List<Ticket> tickets) {
        this.screeningTime = screeningTime;
        this.threeDFormat = threeDFormat;
        this.availableSeats = availableSeats;
        this.tickets = tickets;
    }

    public LocalDateTime getScreeningTime() {
        return screeningTime;
    }

    public void setScreeningTime(LocalDateTime screeningTime) {
        this.screeningTime = screeningTime;
    }

    public Boolean getThreeDFormat() {
        return threeDFormat;
    }

    public void setThreeDFormat(Boolean threeDFormat) {
        this.threeDFormat = threeDFormat;
    }

    public Integer getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(Integer availableSeats) {
        this.availableSeats = availableSeats;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }
}