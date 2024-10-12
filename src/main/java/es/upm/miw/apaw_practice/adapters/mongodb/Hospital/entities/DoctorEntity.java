package es.upm.miw.apaw_practice.adapters.mongodb.Hospital.entities;

import java.math.BigDecimal; // Importar BigDecimal
import es.upm.miw.apaw_practice.domain.models.Hospital.Doctor; // Importar Doctor

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

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public String getHospitalId() {
        return hospitalId;
    }

    public void setHospitalId(String hospitalId) {
        this.hospitalId = hospitalId;
    }
}
