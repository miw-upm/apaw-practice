package es.upm.miw.apaw_practice.domain.models.Hospital;

import java.util.ArrayList;
import java.util.List;

public class Hospital {
    private String id;
    private String name;
    private String location;
    private Integer capacity;
    private List<Doctor> doctors; // List of doctors in the hospital
    private List<Patient> patients; // List of patients in the hospital

    public Hospital() {
        this.doctors = new ArrayList<>();
        this.patients = new ArrayList<>();
    }

    public Hospital(String name, String location, Integer capacity, List<Doctor> doctors, List<Patient> patients) {
        this.name = name;
        this.location = location;
        this.capacity = capacity;
        this.doctors = doctors;
        this.patients = patients;
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

    @Override
    public String toString() {
        return "Hospital{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", capacity=" + capacity +
                ", doctors=" + doctors +
                ", patients=" + patients +
                '}';
    }
}
