package es.upm.miw.apaw_practice.adapters.mongodb.Hospital.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Document
public class HospitalEntity {

    @Id
    private String id;
    private String name;
    private String address;
    private Integer capacity;

    @DBRef
    private List<DoctorEntity> doctorEntities;

    public HospitalEntity(String name, String address, Integer capacity, List<DoctorEntity> doctorEntities) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.address = address;
        this.capacity = capacity;
        this.doctorEntities = doctorEntities;
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

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public List<DoctorEntity> getDoctorEntities() {
        return doctorEntities;
    }

    public void setDoctorEntities(List<DoctorEntity> doctorEntities) {
        this.doctorEntities = doctorEntities;
    }

    public Hospital toHospital() {
        List<Doctor> doctors = this.doctorEntities.stream()
                .map(DoctorEntity::toDoctor)
                .collect(Collectors.toList());
        return new Hospital(name, address, capacity, doctors);
    }

    @Override
    public String toString() {
        return "HospitalEntity{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", capacity=" + capacity +
                ", doctorEntities=" + doctorEntities +
                '}';
    }
}
