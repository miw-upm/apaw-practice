package es.upm.miw.apaw_practice.adapters.mongodb.Hospital.entities;

import es.upm.miw.apaw_practice.domain.models.Hospital.Patient;
import es.upm.miw.apaw_practice.domain.models.Hospital.Appointment;
import org.springframework.beans.BeanUtils;

import java.time.LocalDate;
import java.util.UUID;

public class PatientEntity {

    private String id;
    private String name;
    private LocalDate dateOfBirth;
    private boolean insured;

    public PatientEntity() {
    }



    public PatientEntity(Patient patient) {
        BeanUtils.copyProperties(patient, this);
        this.id = patient.getId() == null ? UUID.randomUUID().toString() : patient.getId();
    }

    public Patient toPatient() {
        return new Patient(
                this.id,
                this.name,
                this.dateOfBirth,
                this.insured
        );
    }

    // Populate PatientEntity from domain model (Patient)
    public void fromPatient(Patient patient) {
        BeanUtils.copyProperties(patient, this);
    }

    // Getters and setters
    public String getId() {
        return this.id;
    }

    public void setId(String id) { // Fixed parameter syntax
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

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return this == obj || (obj != null && getClass() == obj.getClass() && id.equals(((PatientEntity) obj).id));
    }
    public Patient toPatient(Appointment appointment) {
        return new Patient(
                this.id,
                this.name,
                this.dateOfBirth,
                this.insured,
                appointment
        );
    }

    @Override
    public String toString() {
        return "PatientEntity{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", insured=" + insured +
                '}';
    }
}
