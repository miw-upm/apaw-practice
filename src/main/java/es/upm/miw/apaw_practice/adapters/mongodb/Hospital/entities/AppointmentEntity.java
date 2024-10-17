package es.upm.miw.apaw_practice.adapters.mongodb.Hospital.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import es.upm.miw.apaw_practice.domain.models.Hospital.Appointment;
import java.time.LocalDate;
import java.time.LocalTime;

@Document
public class AppointmentEntity {
    @Id
    private Integer id;
    private LocalDate date;
    private LocalTime time;
    private String location;

    private String patientDni;
    private String doctorDni;

    public AppointmentEntity() {
        // Constructor vacío para MongoDB
    }

    public AppointmentEntity(Integer id, LocalDate date, LocalTime time, String location, String patientDni, String doctorDni) {
        this.id = id;
        this.date = date;
        this.time = time;
        this.location = location;
        this.patientDni = patientDni;
        this.doctorDni = doctorDni;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public String getPatientDni() {
        return patientDni;
    }

    public void setPatientDni(String patientDni) {
        this.patientDni = patientDni;
    }

    public String getDoctorDni() {
        return doctorDni;
    }

    public void setDoctorDni(String doctorDni) {
        this.doctorDni = doctorDni;
    }

    public Appointment toAppointment() {

        return new Appointment(
                String.valueOf(this.id),
                this.date,
                this.time,
                this.location
        );
    }
}
