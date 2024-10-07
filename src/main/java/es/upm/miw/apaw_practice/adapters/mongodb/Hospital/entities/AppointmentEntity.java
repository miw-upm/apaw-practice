package es.upm.miw.apaw_practice.adapters.mongodb.Hospital.entities;

import es.upm.miw.apaw_practice.domain.models.hospital.Appointment;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.UUID;

@Document
public class AppointmentEntity {

    @Id
    private String id;

    private LocalDateTime date;
    private String description;

    @DBRef
    private DoctorEntity doctorEntity;

    @DBRef
    private PatientEntity patientEntity;

    public AppointmentEntity(LocalDateTime date, String description, DoctorEntity doctorEntity, PatientEntity patientEntity) {
        this.id = UUID.randomUUID().toString();
        this.date = date;
        this.description = description;
        this.doctorEntity = doctorEntity;
        this.patientEntity = patientEntity;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public DoctorEntity getDoctorEntity() {
        return doctorEntity;
    }

    public void setDoctorEntity(DoctorEntity doctorEntity) {
        this.doctorEntity = doctorEntity;
    }

    public PatientEntity getPatientEntity() {
        return patientEntity;
    }

    public void setPatientEntity(PatientEntity patientEntity) {
        this.patientEntity = patientEntity;
    }

    public Appointment toAppointment() {
        return new Appointment(date, description, doctorEntity.toDoctor(), patientEntity.toPatient());
    }

    @Override
    public String toString() {
        return "AppointmentEntity{" +
                "id='" + id + '\'' +
                ", date=" + date +
                ", description='" + description + '\'' +
                ", doctorEntity=" + doctorEntity +
                ", patientEntity=" + patientEntity +
                '}';
    }
}
