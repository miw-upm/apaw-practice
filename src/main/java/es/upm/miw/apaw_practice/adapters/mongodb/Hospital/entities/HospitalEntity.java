package es.upm.miw.apaw_practice.adapters.mongodb.Hospital.entities;

import org.springframework.data.annotation.Id;
import es.upm.miw.apaw_practice.domain.models.Hospital.Doctor;
import es.upm.miw.apaw_practice.domain.models.Hospital.Patient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.List;

@Document
public class HospitalEntity {
    @Id
    private String id;
    private String name;
    private String location;
    private Integer capacity;

    @DBRef // Reference to doctors
    private List<Doctor> doctors;

    @DBRef // Reference to patients
    private List<Patient> patients;

    public HospitalEntity() {
        // Empty constructor for the framework
    }

    // Constructor accepting a Hospital model
    public HospitalEntity(Hospital hospital) {
        this.id = hospital.getId();
        this.name = hospital.getName();
        this.location = hospital.getLocation();
        this.capacity = hospital.getCapacity();
    }

    public HospitalEntity(String name, String location, Integer capacity) {
        this.name = name;
        this.location = location;
        this.capacity = capacity;
    }

    // Getters and Setters
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public List<Doctor> getDoctors() {
        return doctors;
    }

    public void setDoctors(List<Doctor> doctors) {
        this.doctors = doctors;
    }

    public List<Patient> getPatients() {
        return patients;
    }

    public void setPatients(List<Patient> patients) {
        this.patients = patients;
    }

    // Method to convert to Hospital model
    public Hospital toHospital() {
        return new Hospital(this.id, this.name, this.location, this.capacity);
    }
}
