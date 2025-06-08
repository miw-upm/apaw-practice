package es.upm.miw.apaw_practice.adapters.mongodb.cinema.entities;

import java.time.LocalDateTime;
import java.util.List;

public class ScreeningEntity {
    private LocalDateTime screeningTime;
    private Boolean threeDFormat;
    private Integer availableSeats;
    private List<TicketEntity> tickets;

    public ScreeningEntity() {}

    public ScreeningEntity(LocalDateTime screeningTime, Boolean threeDFormat, Integer availableSeats, List<TicketEntity> tickets) {
        this.screeningTime = screeningTime;
        this.threeDFormat = threeDFormat;
        this.availableSeats = availableSeats;
        this.tickets = tickets;
    }

    // Getters and setters

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

    public List<TicketEntity> getTickets() {
        return tickets;
    }

    public void setTickets(List<TicketEntity> tickets) {
        this.tickets = tickets;
    }
}