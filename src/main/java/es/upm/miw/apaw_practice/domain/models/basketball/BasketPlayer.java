package es.upm.miw.apaw_practice.domain.models.basketball;

public class BasketPlayer {
    private String dni;
    private String name;
    private int dorsal;
    private int points;

    public BasketPlayer(String dni, String name, int dorsal, int points) {
        this.dni = dni;
        this.name = name;
        this.dorsal = dorsal;
        this.points = 0;
    }

    public BasketPlayer() {

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
}
