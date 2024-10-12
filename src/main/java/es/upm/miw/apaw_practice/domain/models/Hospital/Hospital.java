package es.upm.miw.apaw_practice.domain.models.Hospital;

import java.util.List;

public class Hospital {
    private String name;
    private String address;
    private int capacity;
    private List<Doctor> doctors;
    private List<Patient> patients;

    public Hospital() {
        // Empty constructor for framework
    }

    public Hospital(String name, String location, int capacity, List<Doctor> doctors, List<Patient> patients) {
        this.name = name;
        this.location = location;
        this.capacity = capacity;
        this.doctors = doctors;
        this.patients = patients;
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

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
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
                "name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", capacity=" + capacity +
                ", doctors=" + doctors +
                ", patients=" + patients +
                '}';
    }
}
