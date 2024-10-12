package es.upm.miw.apaw_practice.adapters.mongodb.Hospital.entities;

import es.upm.miw.apaw_practice.domain.models.Hospital.Doctor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Document
public class DoctorEntity {
    @Id
    private String id;
    private String name;
    private BigDecimal salary; // Asegúrate de que sea BigDecimal
    private String hospitalId;

    public DoctorEntity() {
        // Constructor vacío para el marco
    }

    // Constructor ajustado para recibir parámetros
    public DoctorEntity(String name, BigDecimal salary, String hospitalId) {
        this.name = name;
        this.salary = salary;
        this.hospitalId = hospitalId;
    }

    // Método para convertir DoctorEntity a Doctor
    public Doctor toDoctor() {
        return new Doctor(this.name, this.salary, this.hospitalId);
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
