package es.upm.miw.apaw_practice.adapters.mongodb.veterinary_clinic.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.UUID;

@Document
public class AnimalEntity {

    @Id
    private String id;
    @Indexed(unique = true)
    private String name;
    private int age;
    private LocalDateTime dateOfService;
    @DBRef
    private OwnerEntity ownerEntity;

    public AnimalEntity() {
        //empty from framework
    }

    public AnimalEntity(OwnerEntity ownerEntity, String name, int age, LocalDateTime dateOfService) {
        this.ownerEntity = ownerEntity;
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.age = age;
        this.dateOfService = dateOfService;
    }

    public OwnerEntity getOwnerEntity() {
        return ownerEntity;
    }

    public void setOwnerEntity(OwnerEntity ownerEntity) {
        this.ownerEntity = ownerEntity;
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

    @Override
    public String toString() {
        return "AnimalEntity{" +
                "ownerEntity=" + ownerEntity +
                ", id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", dateOfService=" + dateOfService +
                '}';
    }
}