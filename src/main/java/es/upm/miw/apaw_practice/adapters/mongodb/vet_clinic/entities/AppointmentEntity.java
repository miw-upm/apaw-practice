package es.upm.miw.apaw_practice.adapters.mongodb.vet_clinic.entities;


import es.upm.miw.apaw_practice.domain.models.vet_clinic.Appointment;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Document
public class AppointmentEntity {
    @Id
    private String id;
    private LocalDate date;
    private LocalTime hour;
    private Boolean consumed;
    @DBRef
    private PetEntity petEntity;

    public AppointmentEntity() {
        //empty for framework
    }

    public AppointmentEntity(Appointment appointment) {
        BeanUtils.copyProperties(appointment, this);
        this.id = UUID.randomUUID().toString();
    }

    public AppointmentEntity(LocalDate date, LocalTime hour, Boolean consumed, PetEntity petEntity) {
        this.id = UUID.randomUUID().toString();
        this.date = date;
        this.hour = hour;
        this.consumed = consumed;
        this.petEntity = petEntity;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getHour() {
        return hour;
    }

    public void setHour(LocalTime hour) {
        this.hour = hour;
    }

    public Boolean getConsumed() {
        return consumed;
    }

    public void setConsumed(Boolean consumed) {
        this.consumed = consumed;
    }

    public PetEntity getPet() {
        return petEntity;
    }

    public void setPet(PetEntity petEntity) {
        this.petEntity = petEntity;
    }

    @Override
    public String toString() {
        return "AppointmentEntity{" +
                "id='" + id + '\'' +
                ", date=" + date +
                ", hour=" + hour +
                ", consumed=" + consumed +
                ", petEntity=" + petEntity +
                '}';
    }

    public Appointment toAppointment() {
        Appointment appointment = new Appointment();
        BeanUtils.copyProperties(this, appointment);
        return appointment;
    }

    public void fromAppointment(Appointment appointment) {
        BeanUtils.copyProperties(appointment, this);
    }
}
