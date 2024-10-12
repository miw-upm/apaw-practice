package es.upm.miw.apaw_practice.adapters.mongodb.Hospital.entities;

import es.upm.miw.apaw_practice.domain.models.Hospital.Appointment;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Document
public class AppointmentEntity {

    @Id
    private String id;
    private String patientDni;
    private LocalDate date;
    private LocalTime time;
    private String room;

    public AppointmentEntity() {
        // Empty constructor for framework
    }

    // Constructor ajustado para recibir parámetros
    public AppointmentEntity(String patientDni, LocalDate date, LocalTime time, String room) {
        this.patientDni = patientDni;
        this.date = date;
        this.time = time;
        this.room = room;
    }

    public AppointmentEntity(Appointment appointment) {
        BeanUtils.copyProperties(appointment, this);
        this.id = UUID.randomUUID().toString(); // Ensure a new ID is generated
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

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public Appointment toAppointment() {
        return new Appointment(this.id, this.date, this.time, this.location);
    }

    @Override
    public String toString() {
        return "AppointmentEntity{" +
                "id='" + id + '\'' +
                ", date=" + date +
                ", time=" + time +
                ", location='" + location + '\'' +
                ", patientId='" + patientId + '\'' +
                '}';
    }
}

