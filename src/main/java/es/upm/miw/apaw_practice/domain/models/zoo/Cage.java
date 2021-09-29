package es.upm.miw.apaw_practice.domain.models.zoo;

import java.time.LocalDate;
import java.util.ArrayList;

public class Cage {

    private Double size;
    private String locationCode;
    private LocalDate nextFumigation;
    private Boolean cleaned;
    private Caretaker caretaker;
    private ArrayList<Animal> animals;

    public Cage(Double size, String locationCode, Caretaker caretaker) {
        this.size = size;
        this.locationCode = locationCode;
        this.caretaker = caretaker;
        this.nextFumigation = LocalDate.MAX;
        this.cleaned = false;
        this.animals = new ArrayList<>();
    }

    public Double getSize() {
        return size;
    }

    public void setSize(Double size) {
        this.size = size;
    }

    public String getLocationCode() {
        return locationCode;
    }

    public void setLocationCode(String locationCode) {
        this.locationCode = locationCode;
    }

    public LocalDate getNextFumigation() {
        return nextFumigation;
    }

    public void setNextFumigation(LocalDate nextFumigation) {
        this.nextFumigation = nextFumigation;
    }

    public Boolean getCleaned() {
        return cleaned;
    }

    public void setCleaned(Boolean cleaned) {
        this.cleaned = cleaned;
    }

    public Caretaker getCaretaker() {
        return caretaker;
    }

    public void setCaretaker(Caretaker caretaker) {
        this.caretaker = caretaker;
    }

    public ArrayList<Animal> getAnimals() {
        return animals;
    }

    public void setAnimals(ArrayList<Animal> animals) {
        this.animals = animals;
    }

    public void addAnimal(Animal animal) {
        this.animals.add(animal);
    }

    public boolean removeAnimal(Animal animal) {
        return this.animals.remove(animal);
    }

    @Override
    public String toString() {
        return "Cage{" +
                "size=" + size +
                ", locationCode='" + locationCode + '\'' +
                ", nextFumigation=" + nextFumigation.toString() +
                ", cleaned=" + cleaned +
                caretaker.toString() +
                ", animals=" + animals.toString() +
                '}';
    }
}
