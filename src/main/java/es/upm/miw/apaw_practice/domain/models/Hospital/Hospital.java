package es.upm.miw.apaw_practice.domain.models.Hospital;
import java.util.List;

public class Hospital {
    private String name;
    private String location;
    private int capacity;
    private List<Doctor> doctors;   // Relación N:N con Doctor
    private List<Patient> patients; // Relación 1:N con Patient

    public Hospital(String name, String location, int capacity) {
        this.name = name;
        this.location = location;
        this.capacity = capacity;
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

    @java.lang.Override
    public java.lang.String toString() {
        return "Hospital{" +
                "name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", capacity=" + capacity +
                ", doctors=" + doctors +
                ", patients=" + patients +
                '}';
    }
}
