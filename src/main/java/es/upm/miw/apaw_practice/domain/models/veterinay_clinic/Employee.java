package es.upm.miw.apaw_practice.domain.models.veterinay_clinic;

import java.util.List;

public class Employee {

    private String id;
    private String name;
    private boolean isDoctor;
    private List<Animal> animals;

    public Employee() {
        //empty from framework
    }

    public Employee(String id, String name, boolean isDoctor, List<Animal> animals) {
        this.id = id;
        this.name = name;
        this.isDoctor = isDoctor;
        this.animals = animals;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isDoctor() {
        return isDoctor;
    }

    public void setDoctor(boolean doctor) {
        isDoctor = doctor;
    }

    public List<Animal> getAnimals() {
        return animals;
    }

    public void setAnimals(List<Animal> animals) {
        this.animals = animals;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", isDoctor=" + isDoctor +
                ", animals=" + animals +
                '}';
    }
}