package es.upm.miw.apaw_practice.adapters.mongodb.Hospital.entities;

import es.upm.miw.apaw_practice.domain.models.Hospital.Hospital;
import es.upm.miw.apaw_practice.domain.models.Hospital.Doctor;
import es.upm.miw.apaw_practice.domain.models.Hospital.Patient;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Document
public class HospitalEntity {

    @Id
    private String id;

    @Indexed(unique = true)
    private String name;
    private String address;  // Ensure it is 'address' and not 'location'
    private Integer capacity;
    private List<DoctorEntity> doctors;
    private List<PatientEntity> patients;

    public HospitalEntity() {
        // Empty constructor for framework
    }

    public HospitalEntity(String name, String address, Integer capacity, List<DoctorEntity> doctors) {
        this.name = name;
        this.address = address;
        this.capacity = capacity;
        this.doctors = doctors;
        this.patients = null;
    }

    public HospitalEntity(Hospital hospital) {
        this.id = hospital.getId();
        this.name = hospital.getName();
        this.doctors = hospital.getDoctors().stream()
                .map(DoctorEntity::new)  // Convert Doctor models to DoctorEntities
                .collect(Collectors.toList());
        this.patients = hospital.getPatients().stream()
                .map(PatientEntity::new)  // Convert Patient models to PatientEntities
                .collect(Collectors.toList());
    }

    public Hospital toHospital() {
        List<Doctor> doctorModels = this.doctors.stream()
                .map(DoctorEntity::toDoctor)  // Convert DoctorEntities to Doctor models
                .collect(Collectors.toList());
        List<Patient> patientModels = this.patients.stream()
                .map(PatientEntity::toPatient)  // Convert PatientEntities to Patient models
                .collect(Collectors.toList());
        return new Hospital(this.id, this.name, doctorModels, patientModels);
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
    public String toString() {
        return "HospitalEntity{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", doctors=" + doctors +
                ", patients=" + patients +
                '}';
    }
}
