package es.upm.miw.apaw_practice.domain.models.Hospital;
import java.util.List;

public class Hospital {
    private String name;
    private String location;
    private int capacity;
    private List<Doctor> Doctores;
    private List<Patient> patients;

    public Hospital(String name, String location, int capacity, List<Doctor> doctores, List<Patient> patients) {
        this.name = name;
        this.location = location;
        this.capacity = capacity;
        this.Doctores = doctores;
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

    public List<Doctor> getDoctores() {
        return Doctores;
    }

    public void setDoctores(List<Doctor> doctores) {
        Doctores = doctores;
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
                ", Doctores=" + Doctores +
                ", patients=" + patients +
                '}';
    }
}