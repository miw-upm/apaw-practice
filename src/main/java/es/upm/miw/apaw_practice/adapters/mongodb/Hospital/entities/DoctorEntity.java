package es.upm.miw.apaw_practice.adapters.mongodb.Hospital.entities;

import java.math.BigDecimal;
import es.upm.miw.apaw_practice.domain.models.Hospital.Doctor;

public class DoctorEntity {
    private String dni;
    private String fullname;
    private BigDecimal salary;

    public DoctorEntity() {
        // Empty constructor for framework
    }

    public DoctorEntity(String dni, String fullname, BigDecimal salary) {
        this.dni = dni;
        this.fullname = fullname;
        this.salary = salary;
    }

    public Doctor toDoctor() {
        return new Doctor(this.dni, this.fullname, this.salary);
    }

    // Getters and Setters
    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }
}
