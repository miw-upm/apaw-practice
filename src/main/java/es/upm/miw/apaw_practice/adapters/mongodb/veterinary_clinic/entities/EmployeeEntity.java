package es.upm.miw.apaw_practice.adapters.mongodb.veterinary_clinic.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.UUID;

@Document
public class EmployeeEntity {

    @Id
    private String id;
    @Indexed(unique = true)
    private String name;
    private boolean isDoctor;
    @DBRef
    private List<AnimalEntity> animalEntities;

    public EmployeeEntity() {
        //empty from framework
    }

    public EmployeeEntity(String name, boolean isDoctor, List<AnimalEntity> animalEntities) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.isDoctor = isDoctor;
        this.animalEntities = animalEntities;
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

    public List<AnimalEntity> getAnimalEntities() {
        return animalEntities;
    }

    public void setAnimalEntities(List<AnimalEntity> animalEntities) {
        this.animalEntities = animalEntities;
    }

    @Override
    public boolean equals(Object object) {
        return this == object || object != null && getClass() == object.getClass() &&
                (name.equals(((EmployeeEntity) object).name));
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public String toString() {
        return "EmployeeEntity{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", isDoctor=" + isDoctor +
                ", animalEntities=" + animalEntities +
                '}';
    }
}