package es.upm.miw.apaw_practice.domain.models.night_life;

public class Club {
    public String name;
    public int capacity;
    public boolean isOpen;
    private Owner owner;

    public Club() {
        //empty for framework
    }

    public Club(String name, int capacity, boolean isOpen, Owner owner) {
        this.name = name;
        this.capacity = capacity;
        this.isOpen = isOpen;
        this.owner = owner;
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
    public Owner getOwner() {
        return owner;
    }
    public void setOwner(Owner owner) {
        this.owner = owner;
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
