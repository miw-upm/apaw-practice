package es.upm.miw.apaw_practice.domain.models.art_museum;

public class Museum {
    private String name;
    private Integer capacity;
    private Boolean isOpen;
    private Exhibition exhibition;

    public Museum() {
        //empty for framework
    }

    public Museum(String name, Integer capacity, Boolean isOpen, Exhibition exhibition) {
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

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Boolean getOpen() {
        return isOpen;
    }

    public void setOpen(Boolean open) {
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
