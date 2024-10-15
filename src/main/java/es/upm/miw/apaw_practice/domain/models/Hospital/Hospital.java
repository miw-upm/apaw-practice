package es.upm.miw.apaw_practice.domain.models.Hospital;

import java.util.List;

public class Hospital {
    private String id;
    private String name;
    private String location;
    private Integer capacity;
    private List<Doctor> doctors;
    private List<Patient> patients;

    // Constructor


    public Hospital(String id, String name, String location, Integer capacity, List<Doctor> doctors, List<Patient> patients) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.capacity = capacity;
        this.doctors = doctors;
        this.patients = patients;
    }

    // Métodos get
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



}
