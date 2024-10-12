package es.upm.miw.apaw_practice.adapters.mongodb.Hospital.entities;

import es.upm.miw.apaw_practice.domain.models.Hospital.Doctor;

public class DoctorEntity {
    private String id;
    private String name;
    private BigDecimal salary;
    private String hospitalId;

    // Constructor que acepta un objeto Doctor
    public DoctorEntity(Doctor doctor) {
        this.id = doctor.getId();
        this.name = doctor.getName();
        this.salary = doctor.getSalary();
        this.hospitalId = doctor.getHospitalId();
    }

    // Método para convertir DoctorEntity a Doctor
    public Doctor toDoctor() {
        return new Doctor(this.id, this.name, this.salary, this.hospitalId);
    }

    // Getters y Setters
}
