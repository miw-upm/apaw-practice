package es.upm.miw.apaw_practice.domain.models.hospital;

import java.util.List;

public class Hospital {

    private String name;
    private String address;
    private Integer availableRooms;
    private List<Patient> patients;

    public Hospital(){
        //empty for framework
    }

    public Hospital(String name, String address, Integer availableRooms, List<Patient> patients) {
        this.name = name;
        this.address = address;
        this.availableRooms = availableRooms;
        this.patients = patients;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getAvailableRooms() {
        return availableRooms;
    }

    public void setAvailableRooms(Integer availableRooms) {
        this.availableRooms = availableRooms;
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
                "name='" + this.name + '\'' +
                ", address='" + this.address + '\'' +
                ", availableRooms=" + this.availableRooms +
                '}';
    }
}
