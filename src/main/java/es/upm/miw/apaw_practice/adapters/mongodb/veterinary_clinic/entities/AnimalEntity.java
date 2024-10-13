package es.upm.miw.apaw_practice.adapters.mongodb.veterinary_clinic.entities;

import es.upm.miw.apaw_practice.domain.models.veterinay_clinic.Animal;
import org.springframework.beans.BeanUtils;
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
    private OwnerClinicEntity ownerClinicEntity;

    public AnimalEntity() {
        //empty from framework
    }

    public AnimalEntity(String name, int age, LocalDateTime dateOfService, OwnerClinicEntity ownerClinicEntity) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.age = age;
        this.dateOfService = dateOfService;
        this.ownerClinicEntity = ownerClinicEntity;
    }

    public OwnerClinicEntity getOwnerEntity() {
        return ownerClinicEntity;
    }

    public void setOwnerEntity(OwnerClinicEntity ownerClinicEntity) {
        this.ownerClinicEntity = ownerClinicEntity;
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

    public Animal toAnimal() {
        Animal animal = new Animal();
        BeanUtils.copyProperties(this, animal);
        if(this.ownerClinicEntity != null) {
            animal.setOwnerClinic(this.ownerClinicEntity.toOwner());
        }
        return animal;
    }

    @Override
    public String toString() {
        return "AnimalEntity{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", dateOfService=" + dateOfService +
                ", ownerClinicEntity=" + ownerClinicEntity +
                '}';
    }
}