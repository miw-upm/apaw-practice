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
}

