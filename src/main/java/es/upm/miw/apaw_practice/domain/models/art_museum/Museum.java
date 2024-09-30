package es.upm.miw.apaw_practice.domain.models.art_museum;

public class Museum {
    private String name;
    private int capacity;
    private boolean isOpen;
    private Exhibition exhibition;

    public Museum() {
        //empty for framework
    }

    public Museum(String name, int capacity, boolean isOpen, Exhibition exhibition) {
        this.name = name;
        this.capacity = capacity;
        this.isOpen = isOpen;
        this.exhibition = exhibition;
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

    public Exhibition getExhibition() {
        return exhibition;
    }

    public void setExhibition(Exhibition exhibition) {
        this.exhibition = exhibition;
    }

    @Override
    public String toString() {
        return "Museum{" +
                "name='" + name + '\'' +
                ", capacity=" + capacity +
                ", isOpen=" + isOpen +
                ", exhibition=" + exhibition +
                '}';
    }
}
