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

    private String location;
    private Integer capacity;
    private List<DoctorEntity> doctors;
    private List<PatientEntity> patients;

    public HospitalEntity() {
        // Empty constructor for framework
    }

    public HospitalEntity(Hospital hospital) {
        this.id = UUID.randomUUID().toString();
        this.name = hospital.getName();
        this.location = hospital.getLocation();
        this.capacity = hospital.getCapacity();
        // Conversión personalizada de doctores
        this.doctors = hospital.getDoctors().stream()
                .map(Doctor::toDoctorEntity)
                .collect(Collectors.toList());
        // Conversión personalizada de pacientes
        this.patients = hospital.getPatients().stream()
                .map(Patient::toPatientEntity)
                .collect(Collectors.toList());
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

    // Conversión de entidad a modelo de dominio
    public Hospital toHospital() {
        Hospital hospital = new Hospital();
        hospital.setName(this.name);
        hospital.setLocation(this.location);
        hospital.setCapacity(this.capacity);
        // Conversión personalizada de doctores
        hospital.setDoctors(this.doctors.stream()
                .map(DoctorEntity::toDoctor)
                .collect(Collectors.toList()));
        // Conversión personalizada de pacientes
        hospital.setPatients(this.patients.stream()
                .map(PatientEntity::toPatient)
                .collect(Collectors.toList()));
        return hospital;
    }

    public void fromHospital(Hospital hospital) {
        this.name = hospital.getName();
        this.location = hospital.getLocation();
        this.capacity = hospital.getCapacity();
        // Conversión personalizada de doctores
        this.doctors = hospital.getDoctors().stream()
                .map(Doctor::toDoctorEntity)
                .collect(Collectors.toList());
        // Conversión personalizada de pacientes
        this.patients = hospital.getPatients().stream()
                .map(Patient::toPatientEntity)
                .collect(Collectors.toList());
    }
    public PatientEntity convertToPatientEntity(Patient patient) {
        return patient.toPatientEntity();
    }

    @Override
    public String toString() {
        return "HospitalEntity{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", capacity=" + capacity +
                '}';
    }
}
