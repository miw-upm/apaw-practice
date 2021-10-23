package es.upm.miw.apaw_practice.domain.models.cinema;

import java.util.List;

public class Screen {
    private Integer number;
    private Integer flat;
    private Integer numberOfSeats;
    private Boolean full;
    private List<Spectator> spectators;

    public Screen() {
        //empty for framework
    }

    public Screen(Integer number, Integer flat, Integer numberOfSeats, Boolean full, List<Spectator> spectators) {
        this.number = number;
        this.flat = flat;
        this.numberOfSeats = numberOfSeats;
        this.full = full;
        this.spectators = spectators;
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

    public List<Spectator> getSpectators() {
        return spectators;
    }

    public void setSpectators(List<Spectator> spectators) {
        this.spectators = spectators;
    }

    public Boolean getFull() {
        return full;
    }

    public void setFull(Boolean full) {
        this.full = full;
    }

    @Override
    public String toString() {
        return "Screen{" +
                "screenNumber=" + number +
                ", flat=" + flat +
                ", numberOfSeats=" + numberOfSeats +
                ", full=" + full +
                ", spectators=" + spectators +
                '}';
    }

}
