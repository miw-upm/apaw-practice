package es.upm.miw.apaw_practice.adapters.mongodb.cinema.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.UUID;

@Document
public class ScreenEntity {
    @Id
    private String id;
    @Indexed(unique = true)
    private Integer number;
    private Integer flat;
    private Integer numberOfSeats;
    private Boolean full;
    private List<SpectatorEntity> spectators;

    public ScreenEntity(){
        //empty for framework
    }

    public ScreenEntity(Integer number, Integer flat, Integer numberOfSeats, Boolean full, List<SpectatorEntity> spectators) {
        this.id = UUID.randomUUID().toString();
        this.number = number;
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

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
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
                ", screenNumber=" + number +
                ", flat=" + flat +
                ", numberOfSeats=" + numberOfSeats +
                ", full=" + full +
                ", spectators=" + spectators +
                '}';
    }
}
