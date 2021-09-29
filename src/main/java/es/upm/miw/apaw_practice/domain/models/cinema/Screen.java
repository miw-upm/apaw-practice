package es.upm.miw.apaw_practice.domain.models.cinema;

public class Screen {
    private int screenNumber;
    private int flat;
    private int numberOfSeats;

    public Screen(){
        //empty for framework
    }

    public Screen(int screenNumber, int flat, int numberOfSeats) {
        this.screenNumber = screenNumber;
        this.flat = flat;
        this.numberOfSeats = numberOfSeats;
    }

    public int getScreenNumber() {
        return screenNumber;
    }

    public void setScreenNumber(int screenNumber) {
        this.screenNumber = screenNumber;
    }

    public int getFlat() {
        return flat;
    }

    public void setFlat(int flat) {
        this.flat = flat;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    @Override
    public String toString() {
        return "Screen{" +
                "screenNumber=" + screenNumber +
                ", flat=" + flat +
                ", numberOfSeats=" + numberOfSeats +
                '}';
    }
}
