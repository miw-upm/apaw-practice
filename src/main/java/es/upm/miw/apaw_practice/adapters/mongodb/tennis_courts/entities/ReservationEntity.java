package es.upm.miw.apaw_practice.adapters.mongodb.tennis_courts.entities;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class ReservationEntity {

    private String id;
    private String ownerName;
    private LocalDateTime date;
    private Integer duration;
    private List<PlayerEntity> players;

    public ReservationEntity(String ownerName, LocalDateTime date, Integer duration, List<PlayerEntity> players){
        this.id = UUID.randomUUID().toString();
        this.ownerName = ownerName;
        this.date = date;
        this.duration = duration;
        this.players = players;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public List<PlayerEntity> getPlayers() {
        return players;
    }

    public void setPlayers(List<PlayerEntity> players) {
        this.players = players;
    }
}