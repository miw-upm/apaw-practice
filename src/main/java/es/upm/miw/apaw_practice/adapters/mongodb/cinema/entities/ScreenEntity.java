package es.upm.miw.apaw_practice.adapters.mongodb.cinema.entities;

import es.upm.miw.apaw_practice.domain.models.cinema.Spectator;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.UUID;

@Document
public class ScreenEntity {
    @Id
    private String id;
    @Indexed(unique = true)
    private Integer screenNumber;
    private Integer flat;
    private Integer numberOfSeats;
    private Boolean full;
    private List<SpectatorEntity> spectators;

    public ScreenEntity(){
        //empty for framework
    }

    public ScreenEntity(Integer screenNumber, Integer flat, Integer numberOfSeats, Boolean full, List<SpectatorEntity> spectators) {
        this.id = UUID.randomUUID().toString();
        this.screenNumber = screenNumber;
        this.flat = flat;
        this.numberOfSeats = numberOfSeats;
        this.full = full;
        this.spectators = spectators;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getScreenNumber() {
        return screenNumber;
    }

    public void setScreenNumber(Integer screenNumber) {
        this.screenNumber = screenNumber;
    }

    public Integer getFlat() {
        return flat;
    }

    public void setFlat(Integer flat) {
        this.flat = flat;
    }

    public Integer getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(Integer numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public Boolean getFull() {
        return full;
    }

    public void setFull(Boolean full) {
        this.full = full;
    }

    public List<SpectatorEntity> getSpectators() {
        return spectators;
    }

    public void setSpectators(List<SpectatorEntity> spectators) {
        this.spectators = spectators;
    }

    @Override
    public String toString() {
        return "ScreenEntity{" +
                "id='" + id + '\'' +
                ", screenNumber=" + screenNumber +
                ", flat=" + flat +
                ", numberOfSeats=" + numberOfSeats +
                ", full=" + full +
                ", spectators=" + spectators +
                '}';
    }
}
