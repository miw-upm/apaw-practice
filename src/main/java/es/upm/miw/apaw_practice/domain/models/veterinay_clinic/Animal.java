package es.upm.miw.apaw_practice.domain.models.veterinay_clinic;

import java.time.LocalDateTime;
import java.util.Objects;

public class Animal {

    private String id;
    private String name;
    private int age;
    private LocalDateTime dateOfService;
    private Owner owner;

    public Animal() {
        //empty from framework
    }

    public Animal(String id, String name, int age, LocalDateTime dateOfService, Owner owner) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.dateOfService = dateOfService;
        this.owner = owner;
    }

    public static Animal ofIdAnimal(Animal animal){
        Animal animalDto = new Animal();
        animalDto.setId(animal.getId());
        animalDto.setName(animal.getName());
        return animalDto;
    }

    public void doDefault() {
        if(Objects.isNull(name)){
            this.name = "animalName";
        }
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

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    @Override
    public int hashCode() {
        return this.id.hashCode();
    }

    @Override
    public boolean equals(Object object) {
        return this == object || object != null && getClass() == object.getClass() && (id.equals(((Animal) object).id));
    }

    @Override
    public String toString() {
        return "Animal{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", dateOfService=" + dateOfService +
                ", owner=" + owner +
                '}';
    }
}