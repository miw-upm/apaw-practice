package es.upm.miw.apaw_practice.adapters.mongodb.hospital.entities;

import es.upm.miw.apaw_practice.domain.models.hospital.Hospital;
import es.upm.miw.apaw_practice.domain.models.hospital.Patient;
import nonapi.io.github.classgraph.json.Id;
import org.springframework.beans.BeanUtils;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.List;
import java.util.UUID;

public class HospitalEntity {
    @Id
    private String id;
    private String name;
    private String address;
    private Integer availableRooms;
    @DBRef
    private List<PatientEntity> patients;

    public HospitalEntity(){
        //empty for framework
    }

    public HospitalEntity(String name, String address, Integer availableRooms, List<PatientEntity> patients) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.address = address;
        this.availableRooms = availableRooms;
        this.patients = patients;
    }

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

    public List<PatientEntity> getPatients() {
        return patients;
    }

    public void setPatients(List<PatientEntity> patients) {
        this.patients = patients;
    }

    @Override
    public String toString() {
        return "HospitalEntity{" +
                "id='" + this.id + '\'' +
                ", name='" + this.name + '\'' +
                ", address='" + this.address + '\'' +
                ", availableRooms=" + this.availableRooms +
                ", patients=" + this.patients +
                '}';
    }
}