package es.upm.miw.apaw_practice.adapters.mongodb.Hospital.entities;

import java.time.LocalDate;
import java.time.LocalTime;

public class AppointmentEntity {
    private String patientDni;
    private LocalDate date;
    private LocalTime time;
    private String room;

    // Constructor with four parameters
    public AppointmentEntity(String patientDni, LocalDate date, LocalTime time, String room) {
        this.patientDni = patientDni;
        this.date = date;
        this.time = time;
        this.room = room;
    }

    // Getters and setters
    public String getPatientDni() {
        return patientDni;
    }

    public void setPatientDni(String patientDni) {
        this.patientDni = patientDni;
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

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }
}

