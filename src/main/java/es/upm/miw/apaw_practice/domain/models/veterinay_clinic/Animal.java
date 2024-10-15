package es.upm.miw.apaw_practice.domain.models.veterinay_clinic;

import java.time.LocalDateTime;
import java.util.Objects;

public class Animal {

    private String name;
    private int age;
    private LocalDateTime dateOfService;
    private OwnerClinic ownerClinic;

    public Animal() {
        //empty from framework
    }

    public Animal(String name, int age, LocalDateTime dateOfService, OwnerClinic ownerClinic) {
        this.name = name;
        this.age = age;
        this.dateOfService = dateOfService;
        this.ownerClinic = ownerClinic;
    }

    public static Animal ofName(Animal animal){
        Animal animalDto = new Animal();
        animalDto.setName(animal.getName());
        return animalDto;
    }

    public void doDefault() {
        if(Objects.isNull(name)){
            this.name = "animalName";
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public LocalDateTime getDateOfService() {
        return dateOfService;
    }

    public void setDateOfService(LocalDateTime dateOfService) {
        this.dateOfService = dateOfService;
    }

    public OwnerClinic getOwnerClinic() {
        return ownerClinic;
    }

    public void setOwnerClinic(OwnerClinic ownerClinic) {
        this.ownerClinic = ownerClinic;
    }

    @Override
    public String toString() {
        return "Animal{" +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", dateOfService=" + dateOfService +
                ", ownerClinic=" + ownerClinic +
                '}';
    }
}