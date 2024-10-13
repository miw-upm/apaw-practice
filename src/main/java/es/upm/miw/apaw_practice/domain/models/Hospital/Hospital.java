package es.upm.miw.apaw_practice.domain.models.Hospital;

import java.util.ArrayList;
import java.util.List;

public class Hospital {
    private String id;
    private String name;
    private String location;
    private Integer capacity;
    private List<Doctor> doctors;
    private List<Patient> patients;

    public Hospital() {
        this.doctors = new ArrayList<>();
        this.patients = new ArrayList<>();
    }

    public Hospital(String id, String name, String location, Integer capacity, List<Doctor> doctors, List<Patient> patients) {
        this.id = id; // Ensure you capture the id here
        this.name = name;
        this.location = location;
        this.capacity = capacity;
        this.doctors = doctors;
        this.patients = patients;
    }

    // Getters
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public List<Doctor> getDoctors() {
        return doctors;
    }

    public List<Patient> getPatients() {
        return patients;
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
