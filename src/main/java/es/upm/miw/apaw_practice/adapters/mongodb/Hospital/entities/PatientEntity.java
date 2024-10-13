package es.upm.miw.apaw_practice.adapters.mongodb.Hospital.entities;

import es.upm.miw.apaw_practice.domain.models.Hospital.Patient;
import java.time.LocalDate;

public class PatientEntity {
    private String id;
    private String name;
    private LocalDate birthDate;
    private boolean insured;

    // Constructor to convert from Patient to PatientEntity
    public PatientEntity(Patient patient) {
        this.id = patient.getId();
        this.name = patient.getName();
        this.birthDate = patient.getBirthDate();
        this.insured = patient.isInsured();
    }

    // Default constructor
    public PatientEntity() {}

    // Getters and Setters
    public String getId() { return id; }
    public String getName() { return name; }
    public LocalDate getBirthDate() { return birthDate; }
    public boolean isInsured() { return insured; }

    public void setId(String id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setBirthDate(LocalDate birthDate) { this.birthDate = birthDate; }
    public void setInsured(boolean insured) { this.insured = insured; }

    // Convert back to Patient
    public Patient toPatient() {
        return new Patient(this.id, this.name, this.birthDate, this.insured);
    }
}

