package es.upm.miw.apaw_practice.adapters.mongodb.Hospital.entities;

import es.upm.miw.apaw_practice.domain.models.Hospital.Patient;

public class PatientEntity {
    private String id;
    private String name;
    private LocalDate dateOfBirth;
    private boolean insured;

    // Constructor que acepta un objeto Patient
    public PatientEntity(Patient patient) {
        this.id = patient.getId();
        this.name = patient.getName();
        this.dateOfBirth = patient.getDateOfBirth();
        this.insured = patient.isInsured();
    }

    // Método para convertir PatientEntity a Patient
    public Patient toPatient() {
        return new Patient(this.id, this.name, this.dateOfBirth, this.insured);
    }

    // Getters y Setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public boolean isInsured() {
        return insured;
    }

    public void setInsured(boolean insured) {
        this.insured = insured;
    }
}

