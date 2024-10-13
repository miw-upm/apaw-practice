package es.upm.miw.apaw_practice.adapters.mongodb.Hospital.entities;

import es.upm.miw.apaw_practice.domain.models.Hospital.Hospital;
import es.upm.miw.apaw_practice.domain.models.Hospital.Doctor;
import es.upm.miw.apaw_practice.domain.models.Hospital.Patient;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class HospitalEntity {

    private String id;
    private String name;
    private String address;
    private Integer capacity;
    private List<DoctorEntity> doctors;
    private List<PatientEntity> patients;

    // Default constructor
    public HospitalEntity() {
        this.id = UUID.randomUUID().toString(); // Assign UUID if no id is provided
        this.doctors = List.of();
        this.patients = List.of();
    }

    // Constructor with parameters
    public HospitalEntity(String name, String address, Integer capacity, List<DoctorEntity> doctors) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.address = address;
        this.capacity = capacity;
        this.doctors = doctors;
        this.patients = List.of(); // Initialize with an empty list
    }

    public List<Doctor> getDoctors() {
        return doctors.stream()
                .map(DoctorEntity::toDoctor)
                .collect(Collectors.toList());
    }

    public List<Patient> getPatients() {
        return patients.stream()
                .map(PatientEntity::toPatient)
                .collect(Collectors.toList());
    }

    public Hospital toHospital() {
        return new Hospital(
                this.id,
                this.name,
                this.address,
                this.capacity,
                getDoctors(), // Reuse the method
                getPatients() // Reuse the method
        );
    }

    public void fromHospital(Hospital hospital) {
        BeanUtils.copyProperties(hospital, this);
        this.doctors = hospital.getDoctors().stream()
                .map(DoctorEntity::new)
                .collect(Collectors.toList());
        this.patients = hospital.getPatients().stream()
                .map(PatientEntity::new)
                .collect(Collectors.toList());
    }

    // Getters and Setters
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

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return this == obj || (obj != null && getClass() == obj.getClass() && id.equals(((HospitalEntity) obj).id));
    }

    @Override
    public String toString() {
        return "HospitalEntity{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", capacity=" + capacity +
                ", doctors=" + doctors +
                ", patients=" + patients +
                '}';
    }
}
