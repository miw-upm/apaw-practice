package es.upm.miw.apaw_practice.adapters.mongodb.Hospital.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import es.upm.miw.apaw_practice.domain.models.Hospital.entities.PatientEntity;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;


public class AppointmentEntity {

    @Id
    private String id;

    private LocalDate date;
    private LocalTime time;
    private String location;

    @DBRef
    private PatientEntity patientEntity;

    public AppointmentEntity() {
        // Empty constructor for framework
    }

    public AppointmentEntity(LocalDate date, LocalTime time, String location, PatientEntity patientEntity) {
        this.id = UUID.randomUUID().toString();
        this.date = date;
        this.time = time;
        this.location = location;
        this.patientEntity = patientEntity;
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

    public PatientEntity getPatientEntity() {
        return patientEntity;
    }

    public void setPatientEntity(PatientEntity patientEntity) {
        this.patientEntity = patientEntity;
    }

    // Method to convert this entity to a domain model (similar to toArticleItem)
    public Appointment toAppointment() {
        return new Appointment(this.date, this.time, this.location, this.patientEntity.toPatient());
    }

    @Override
    public String toString() {
        return "AppointmentEntity{" +
                "id='" + id + '\'' +
                ", date=" + date +
                ", time=" + time +
                ", location='" + location + '\'' +
                ", patientEntity=" + patientEntity +
                '}';
    }
}
