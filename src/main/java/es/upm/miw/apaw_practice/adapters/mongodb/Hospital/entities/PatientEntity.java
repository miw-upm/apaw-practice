package es.upm.miw.apaw_practice.adapters.mongodb.Hospital.entities;

import java.time.LocalDate;
import es.upm.miw.apaw_practice.domain.models.Hospital.Appointment;
import es.upm.miw.apaw_practice.domain.models.Hospital.Patient;



public class PatientEntity {
    private String dni;
    private String fullname;
    private LocalDate dateOfBirth;
    private boolean hasInsurance;
    private Appointment appointment; // Reference to Appointment

    public PatientEntity() {
        // Empty constructor for framework
    }

    public PatientEntity(String dni, String fullname, LocalDate dateOfBirth, boolean hasInsurance, Appointment appointment) {
        this.dni = dni;
        this.fullname = fullname;
        this.dateOfBirth = dateOfBirth;
        this.hasInsurance = hasInsurance;
        this.appointment = appointment;
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

    public Appointment getAppointment() {
        return appointment;
    }

    public void setAppointment(Appointment appointment) {
        this.appointment = appointment;
    }

    public Patient toPatient() {
        return new Patient(this.dni, this.fullname, this.dateOfBirth, this.hasInsurance, this.appointment);
    }

    @Override
    public String toString() {
        return "PatientEntity{" +
                "dni='" + dni + '\'' +
                ", fullname='" + fullname + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", hasInsurance=" + hasInsurance +
                ", appointment=" + appointment +
                '}';
    }
}

