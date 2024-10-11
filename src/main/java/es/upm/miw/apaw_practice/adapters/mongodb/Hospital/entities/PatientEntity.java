package es.upm.miw.apaw_practice.adapters.mongodb.Hospital.entities;

import java.time.LocalDate;

public class PatientEntity {
    private String dni;
    private String fullname;
    private LocalDate dateOfBirth;
    private boolean hasInsurance;
    // If appointment is part of PatientEntity, include it here
    // private Appointment appointment;

    // Constructor that accepts parameters
    public PatientEntity(String dni, String fullname, LocalDate dateOfBirth, boolean hasInsurance /*, Appointment appointment */) {
        this.dni = dni;
        this.fullname = fullname;
        this.dateOfBirth = dateOfBirth;
        this.hasInsurance = hasInsurance;
        // Initialize appointment if necessary
        // this.appointment = appointment;
    }

    // Getters and Setters
    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public boolean isHasInsurance() {
        return hasInsurance;
    }

    public void setHasInsurance(boolean hasInsurance) {
        this.hasInsurance = hasInsurance;
    }

    // Include getter and setter for appointment if needed
}

