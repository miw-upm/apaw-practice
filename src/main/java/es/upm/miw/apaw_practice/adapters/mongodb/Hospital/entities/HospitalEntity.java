package es.upm.miw.apaw_practice.adapters.mongodb.Hospital.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
public class HospitalEntity {

    @Id
    private String id;
    private String name;
    private String location;
    private Integer capacity;

    @DBRef
    private List<DoctorEntity> doctors;

    @DBRef
    private List<PatientEntity> patients;

    // Getters, Setters, Constructor, equals, hashCode, toString...

    public HospitalEntity() {
    }

    public HospitalEntity(String id, String name, String location, Integer capacity, List<DoctorEntity> doctors, List<PatientEntity> patients) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.capacity = capacity;
        this.doctors = doctors;
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

    public List<DoctorEntity> getDoctors() {
        return doctors;
    }

    public void setDoctors(List<DoctorEntity> doctors) {
        this.doctors = doctors;
    }

    public List<PatientEntity> getPatients() {
        return patients;
    }

    public void setPatients(List<PatientEntity> patients) {
        this.patients = patients;
    }

    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof HospitalEntity)) return false;
        if (!super.equals(object)) return false;
        HospitalEntity that = (HospitalEntity) object;
        return java.util.Objects.equals(getId(), that.getId()) && java.util.Objects.equals(getName(), that.getName()) && java.util.Objects.equals(getLocation(), that.getLocation()) && java.util.Objects.equals(getCapacity(), that.getCapacity()) && java.util.Objects.equals(getDoctors(), that.getDoctors()) && java.util.Objects.equals(getPatients(), that.getPatients());
    }

    public int hashCode() {
        return Objects.hash(super.hashCode(), getId(), getName(), getLocation(), getCapacity(), getDoctors(), getPatients());
    }

    @java.lang.Override
    public java.lang.String toString() {
        return "HospitalEntity{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", capacity=" + capacity +
                ", doctors=" + doctors +
                ", patients=" + patients +
                '}';
    }
}
