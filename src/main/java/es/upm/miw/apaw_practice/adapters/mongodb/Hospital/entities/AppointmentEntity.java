package es.upm.miw.apaw_practice.adapters.mongodb.Hospital.entities;

import es.upm.miw.apaw_practice.domain.models.Hospital.Appoinment;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Document
public class AppointmentEntity  {

    @Id
    private String id;

    private LocalDate date;
    private LocalTime time;
    private String location;
    private String patientId;

    public AppointmentEntity() {
        // Empty constructor for framework
    }

    public AppointmentEntity(Appointment appointment) {
        BeanUtils.copyProperties(appointment, this);
        this.id = UUID.randomUUID().toString(); // Ensure a new ID is generated
    }

    // Getters and Setters with proper return types

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

    // Conversion methods

    public void fromAppointment(Appointment appointment) {
        BeanUtils.copyProperties(appointment, this);
    }

    public Appointment toAppointment() {
        Appointment appointment = new Appointment();
        BeanUtils.copyProperties(this, appointment);
        return appointment;
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