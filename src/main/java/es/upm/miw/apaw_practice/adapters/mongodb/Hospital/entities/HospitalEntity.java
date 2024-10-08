package es.upm.miw.apaw_practice.adapters.mongodb.Hospital.entities;

import es.upm.miw.apaw_practice.domain.models.Hospital.Hospital;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.List;

public class HospitalEntity {
    @Id
    private String name;
    private String location;
    private int capacity;
    @DBRef
    private List<DoctorEntity> doctores;
    @DBRef
    private List<PatientEntity> patients;

    public HospitalEntity() {
        // empty for framework
    }

    public HospitalEntity(String name, String location, int capacity, List<DoctorEntity> doctores, List<PatientEntity> patients) {
        this.name = name;
        this.location = location;
        this.capacity = capacity;
        this.doctores = doctores;
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

    public List<DoctorEntity> getDoctores() {
        return doctores;
    }

    public void setDoctores(List<DoctorEntity> doctores) {
        this.doctores = doctores;
    }

    public List<PatientEntity> getPatients() {
        return patients;
    }

    public void setPatients(List<PatientEntity> patients) {
        this.patients = patients;
    }

    public void fromHospital(Hospital hospital) {
        BeanUtils.copyProperties(hospital, this);
    }

    public Hospital toHospital() {
        Hospital hospital = new Hospital();
        BeanUtils.copyProperties(this, hospital, "doctores", "patients");
        return hospital;
    }
}
