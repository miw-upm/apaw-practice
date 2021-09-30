package es.upm.miw.apaw_practice.adapters.mongodb.vet_clinic.entities;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.UUID;

@Document
public class VetEntity {
    @Id
    private String id;
    @DBRef
    private List<AppointmentEntity> appointmentEntities;
    @Indexed(unique = true)
    private Integer vetNumber;
    private String name;
    private String surname;

    public VetEntity() {
       //empty for framework
    }

    public VetEntity(Integer vetNumber, String name, String surname, List<AppointmentEntity> appointmentEntities) {
        this.id = UUID.randomUUID().toString();
        this.vetNumber = vetNumber;
        this.name = name;
        this.surname = surname;
        this.appointmentEntities = appointmentEntities;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<AppointmentEntity> getAppointmentEntities() {
        return appointmentEntities;
    }

    public void setAppointmentEntities(List<AppointmentEntity> appointmentEntities) {
        this.appointmentEntities = appointmentEntities;
    }

    public Integer getVetNumber() {
        return vetNumber;
    }

    public void setVetNumber(Integer vetNumber) {
        this.vetNumber = vetNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Override
    public int hashCode() {
        return vetNumber.hashCode();
    }
}
