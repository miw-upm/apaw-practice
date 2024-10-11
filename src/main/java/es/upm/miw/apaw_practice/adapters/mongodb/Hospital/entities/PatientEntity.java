package es.upm.miw.apaw_practice.adapters.mongodb.Hospital.entities;

import java.time.LocalDate;
import es.upm.miw.apaw_practice.domain.models.Hospital.Patient;

public class PatientEntity {
    private String dni;
    private String fullname;
    private LocalDate dateOfBirth;
    private boolean hasInsurance;
    private Appointment appointment; // Ensure that Appointment is defined correctly

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

    // Getters and Setters...

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
