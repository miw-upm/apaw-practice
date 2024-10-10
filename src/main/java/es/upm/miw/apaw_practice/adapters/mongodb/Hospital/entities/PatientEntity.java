package es.upm.miw.apaw_practice.adapters.mongodb.Hospital.entities;

import es.upm.miw.apaw_practice.domain.models.Hospital.Patient;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.UUID;

@Document
public class PatientEntity {

    @Id
    private String id;

    private String fullname;
    private LocalDate dateOfBirth;
    private Boolean hasInsurance;
    private String hospitalId;

    public PatientEntity() {
        // Empty constructor for framework
    }

    public PatientEntity(Patient patient) {
        BeanUtils.copyProperties(patient, this);
        this.id = UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public Boolean getHasInsurance() {
        return hasInsurance;
    }

    public void setHasInsurance(Boolean hasInsurance) {
        this.hasInsurance = hasInsurance;
    }

    public String getHospitalId() {
        return hospitalId;
    }

    public void setHospitalId(String hospitalId) {
        this.hospitalId = hospitalId;
    }

    // Métodos de conversión
    public void fromPatient(Patient patient) {
        BeanUtils.copyProperties(patient, this);
    }

    public Patient toPatient() {
        Patient patient = new Patient();
        BeanUtils.copyProperties(this, patient);
        return patient;
    }

    @Override
    public String toString() {
        return "PatientEntity{" +
                "id='" + id + '\'' +
                ", fullname='" + fullname + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", hasInsurance=" + hasInsurance +
                ", hospitalId='" + hospitalId + '\'' +
                '}';
    }
}

