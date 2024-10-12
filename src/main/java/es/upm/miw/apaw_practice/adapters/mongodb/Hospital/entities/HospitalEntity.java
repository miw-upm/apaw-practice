package es.upm.miw.apaw_practice.adapters.mongodb.Hospital.entities;

import es.upm.miw.apaw_practice.domain.models.Hospital;
import es.upm.miw.apaw_practice.domain.models.Hospital.Doctor;
import es.upm.miw.apaw_practice.domain.models.Hospital.Patient;

import java.util.List;
import java.util.stream.Collectors;

public class HospitalEntity {
    private String id;
    private String name;
    private String address; // Asegúrate de que este campo esté definido
    private Integer capacity; // Asegúrate de que este campo esté definido
    private List<DoctorEntity> doctors;
    private List<PatientEntity> patients; // Inicialízalo como una lista vacía si es necesario

    // Constructor para crear HospitalEntity a partir de parámetros
    public HospitalEntity(String name, String address, Integer capacity, List<DoctorEntity> doctors) {
        this.name = name;
        this.address = address;
        this.capacity = capacity;
        this.doctors = doctors;
        this.patients = List.of(); // Inicializa como una lista vacía
    }

    // Constructor para crear HospitalEntity a partir de un objeto Hospital
    public HospitalEntity(Hospital hospital) {
        this.id = hospital.getId();
        this.name = hospital.getName();
        this.address = hospital.getAddress(); // Asegúrate de que esto esté definido en Hospital
        this.capacity = hospital.getCapacity(); // Asegúrate de que esto esté definido en Hospital
        this.doctors = hospital.getDoctors().stream()
                .map(DoctorEntity::new)
                .collect(Collectors.toList());
        this.patients = hospital.getPatients().stream()
                .map(PatientEntity::new)
                .collect(Collectors.toList());
    }

    // Método para convertir HospitalEntity a Hospital
    public Hospital toHospital() {
        return new Hospital(this.id, this.name, this.address, this.capacity,
                this.doctors.stream().map(DoctorEntity::toDoctor).collect(Collectors.toList()),
                this.patients.stream().map(PatientEntity::toPatient).collect(Collectors.toList()));
    }

    // Getters y Setters

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
}
