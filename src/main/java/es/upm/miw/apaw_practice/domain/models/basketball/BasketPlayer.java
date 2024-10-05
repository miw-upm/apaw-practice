package es.upm.miw.apaw_practice.domain.models.basketball;

import java.util.List;

public class BasketPlayer {
    private String dni;
    private String name;
    private int dorsal;
    private int points;
    private List<BasketMatch> basketMatches;

    public BasketPlayer(String dni, String name, int dorsal, int points, List<BasketMatch> basketMatches) {
        this.dni = dni;
        this.name = name;
        this.dorsal = dorsal;
        this.points = 0;
        this.basketMatches = basketMatches;
    }

    public String getDni() {
        return dni;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDorsal() {
        return dorsal;
    }

    public void setDorsal(int dorsal) {
        this.dorsal = dorsal;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public List<BasketMatch> getBasketMatches() {
        return basketMatches;
    }

    public void setBasketMatches(List<BasketMatch> basketMatches) {
        this.basketMatches = basketMatches;
    }
}
