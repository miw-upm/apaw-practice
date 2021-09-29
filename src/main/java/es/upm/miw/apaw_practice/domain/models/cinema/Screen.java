package es.upm.miw.apaw_practice.domain.models.cinema;

import java.util.List;

public class Screen {
    private Integer screenNumber;
    private Integer flat;
    private Integer numberOfSeats;
    private List<Spectator> spectators;

    public Screen(){
        //empty for framework
    }

    public Screen(Integer screenNumber, Integer flat, Integer numberOfSeats, List<Spectator> spectators) {
        this.screenNumber = screenNumber;
        this.flat = flat;
        this.numberOfSeats = numberOfSeats;
        this.spectators = spectators;
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

    public List<Spectator> getSpectators() {
        return spectators;
    }

    public void setSpectators(List<Spectator> spectators) {
        this.spectators = spectators;
    }

    @Override
    public String toString() {
        return "Screen{" +
                "screenNumber=" + screenNumber +
                ", flat=" + flat +
                ", numberOfSeats=" + numberOfSeats +
                ", spectators=" + spectators +
                '}';
    }
}
