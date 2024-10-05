package es.upm.miw.apaw_practice.domain.models.Hospital;
import java.util.List;

public class Hospital {
    private String name;
    private String location;
    private int capacity;
    private Doctor doctor;
    private List<Patient> patients;

    public Hospital(String name, String location, int capacity, Doctor doctor, List<Patient> patients) {
        this.name = name;
        this.location = location;
        this.capacity = capacity;
        this.doctor = doctor;
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

    public Doctor getDoctors() {
        return doctor;
    }

    public void setDoctors(Doctor doctor) {
        this.doctor = doctor;
    }

    public List<Patient> getPatients() {
        return patients;
    }

    public void setPatients(List<Patient> patients) {
        this.patients = patients;
    }

    @java.lang.Override
    public java.lang.String toString() {
        return "Hospital{" +
                "name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", capacity=" + capacity +
                ", doctor=" + doctor +
                ", patients=" + patients +
                '}';
    }
}
