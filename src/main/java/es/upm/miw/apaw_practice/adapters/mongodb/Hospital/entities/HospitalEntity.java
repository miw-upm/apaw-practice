package es.upm.miw.apaw_practice.adapters.mongodb.Hospital.entities;

import es.upm.miw.apaw_practice.domain.models.Hospital.Hospital;
import es.upm.miw.apaw_practice.domain.models.Hospital.Doctor;
import es.upm.miw.apaw_practice.adapters.mongodb.Hospital.entities.DoctorEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.Hospital.entities.PatientEntity;
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
        this.id = UUID.randomUUID().toString(); // Generate a new UUID
        this.name = name;
        this.address = address;
        this.capacity = capacity;
        this.doctors = doctors;
        this.patients = List.of(); // Initialize with an empty list
    }

    // Convert DoctorEntity list to Doctor list
    // Assuming you have a List<Doctor> doctors in Hospital
    public List<DoctorEntity> getDoctors() {
        return doctors.stream()
                .map(DoctorEntity::new) // Convert from Doctor to DoctorEntity
                .collect(Collectors.toList());
    }


    // Convert PatientEntity list to Patient list
    public List<Patient> getPatients() {
        return patients.stream()
                .map(PatientEntity::toPatient)
                .collect(Collectors.toList());
    }

    // Convert HospitalEntity to Hospital model
    public Hospital toHospital() {
        return new Hospital(
                this.id,
                this.name,
                this.address,
                this.capacity,
                getDoctors(), // Reuse the method to convert doctors
                getPatients()  // Reuse the method to convert patients
        );
    }

    // Populate HospitalEntity from Hospital model
    public void fromHospital(Hospital hospital) {
        BeanUtils.copyProperties(hospital, this); // Copy common properties
        this.doctors = hospital.getDoctors().stream()
                .map(DoctorEntity::new) // Convert to DoctorEntity
                .collect(Collectors.toList());
        this.patients = hospital.getPatients().stream()
                .map(PatientEntity::new) // Convert to PatientEntity
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

    public List<DoctorEntity> getDoctorEntities() {
        return doctors;
    }

    public void setDoctorEntities(List<DoctorEntity> doctors) {
        this.doctors = doctors;
    }

    public List<PatientEntity> getPatientEntities() {
        return patients;
    }

    public void setPatientEntities(List<PatientEntity> patients) {
        this.patients = patients;
    }

    @Override
    public int hashCode() {
        return id.hashCode(); // Use UUID as hash code
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
