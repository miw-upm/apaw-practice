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
    private BigDecimal salary;

    // Constructor vacío para el marco
    public DoctorEntity() {
        // Constructor vacío para el marco
    }

    // Constructor ajustado para recibir un objeto Doctor
    public DoctorEntity(Doctor doctor) {
        this.id = doctor.getId();
        this.name = doctor.getName();
        this.salary = doctor.getSalary();
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
        return salary; // Getter para salary
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary; // Setter para salary
    }

    @Override
    public String toString() {
        return "DoctorEntity{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", salary=" + salary + // Incluir salary en el toString
                '}';
    }
}
