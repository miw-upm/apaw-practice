package es.upm.miw.apaw_practice.domain.models.night_life;

public class Club {
    public String name;
    public int capacity;
    public boolean isOpen;

    public Club() {
        //empty for framework
    }

    public Club(String name, int capacity, boolean isOpen) {
        this.name = name;
        this.capacity = capacity;
        this.isOpen = isOpen;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }

    @Override
    public String toString() {
        return "Club{" +
                "name='" + name + '\'' +
                ", capacity=" + capacity +
                ", isOpen=" + isOpen +
                '}';
    }
}
